#!/bin/bash

# GITHUB_TOKEN is expected to be set as an environment variable
# Example: export GITHUB_TOKEN="your_github_token_here"

# Check if GITHUB_TOKEN is set
if [ -z "${GITHUB_TOKEN}" ]; then
  echo "Error: GITHUB_TOKEN environment variable is not set." >&2
  echo "Please set it before running the script. Example: export GITHUB_TOKEN='your_token'" >&2
  exit 1
fi

START_DATE="2024-12-01"
END_DATE=$(date +%Y-%m-%d) # Get today's date

# Convert dates to seconds since epoch for comparison and iteration
current_epoch=$(date -j -f "%Y-%m-%d" "$START_DATE" +%s)
end_epoch=$(date -j -f "%Y-%m-%d" "$END_DATE" +%s)

echo "Date,Total Count,Python Count,TypeScript Count,JavaScript Count,Java Count" # Header for the output

#+language:Java

while [ "$current_epoch" -le "$end_epoch" ]; do
    # Format the current date for the API query
    query_date=$(date -j -f %s "$current_epoch" +%Y-%m-%d)

    # --- Total Count ---
    response_total=$(curl -s -H "Accept: application/vnd.github.v3+json" \
         -H "Authorization: token ${GITHUB_TOKEN}" \
         "https://api.github.com/search/repositories?q=mcp-server+created:<${query_date}&sort=stars&order=desc")
    total_count=$(jq '.total_count // 0' <<< "$response_total")
    if ! [[ "$total_count" =~ ^[0-9]+$ ]]; then
        echo "Error fetching or parsing total data for date ${query_date}. Response: ${response_total}" >&2
        total_count="Error"
    elif [[ "$total_count" -eq 0 ]]; then
         # Check if count is 0 and if there might be an error message in the response
         error_message=$(jq -r '.message // ""' <<< "$response_total")
         if [[ -n "$error_message" ]]; then
             echo "Warning: Total count is 0 for ${query_date}, potentially due to API error: ${error_message}" >&2
             #echo "Full response: ${response_total}" >&2
             # Optionally treat as error or break
             # total_count="API Error"
         fi
    fi

    # --- Language-specific Counts ---
    languages=("Python" "TypeScript" "JavaScript" "Java")
    lang_counts=()

    for lang in "${languages[@]}"; do
        # Need to handle spaces in language names if any, but these are safe
        query_lang=$(echo "$lang" | sed 's/ /+/g') # Basic URL encoding if needed
        response_lang=$(curl -s -H "Accept: application/vnd.github.v3+json" \
             -H "Authorization: token ${GITHUB_TOKEN}" \
             "https://api.github.com/search/repositories?q=mcp-server+language:${query_lang}+created:<${query_date}&sort=stars&order=desc")

        count=$(jq '.total_count // 0' <<< "$response_lang")
        if ! [[ "$count" =~ ^[0-9]+$ ]]; then
            echo "Error fetching or parsing ${lang} data for date ${query_date}. Response: ${response_lang}" >&2
            count="Error"
        elif [[ "$count" -eq 0 ]]; then
            # Check if count is 0 and if there might be an error message in the response
            error_message=$(jq -r '.message // ""' <<< "$response_lang")
            if [[ -n "$error_message" ]]; then
                 echo "Warning: ${lang} count is 0 for ${query_date}, potentially due to API error: ${error_message}" >&2
                 #echo "Full response: ${response_lang}" >&2
                 # Optionally treat as error or break
                 # count="API Error"
            fi
        fi
        lang_counts+=("$count")
    done

    # Print the date and all counts
    echo "${query_date},${total_count},${lang_counts[0]},${lang_counts[1]},${lang_counts[2]},${lang_counts[3]}"

    # Increment the date by 7 days (7 * 24 * 60 * 60 seconds)
    current_epoch=$((current_epoch + 7 * 24 * 60 * 60))

    # Add a small delay to avoid hitting rate limits too quickly
    sleep 1
done

