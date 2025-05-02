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

echo "Date,Total Count" # Header for the output

#+language:Java

while [ "$current_epoch" -le "$end_epoch" ]; do
    # Format the current date for the API query
    query_date=$(date -j -f %s "$current_epoch" +%Y-%m-%d)

    # Make the API call
    response=$(curl -s -H "Accept: application/vnd.github.v3+json" \
         -H "Authorization: token ${GITHUB_TOKEN}" \
         "https://api.github.com/search/repositories?q=mcp-server+created:<${query_date}&sort=stars&order=desc")

    # Extract the total count of repositories
    # Add error handling in case jq fails or response is unexpected
    total_count=$(jq '.total_count // 0' <<< "$response")
    if ! [[ "$total_count" =~ ^[0-9]+$ ]]; then
        echo "Error fetching or parsing data for date ${query_date}. Response: ${response}" >&2
        total_count="Error"
    fi


    # Print the date and total count
    echo "${query_date},${total_count}"

    # Increment the date by 7 days (7 * 24 * 60 * 60 seconds)
    current_epoch=$((current_epoch + 7 * 24 * 60 * 60))
done

