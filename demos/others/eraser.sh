#!/bin/bash

# Script to remove specified folders and files

# Define the target folders
TARGET_FOLDERS=(".mvn" "src" "target")

# Define the target files
TARGET_FILES=("compose.yaml" "HELP.md" "mvnw" "mvnw.cmd" "pom.xml")

# First, remove folders
echo "Processing folders..."
for FOLDER in "${TARGET_FOLDERS[@]}"; do
    # Check if the folder exists
    if [ -d "$FOLDER" ]; then
        echo "Found $FOLDER directory. Removing..."
        
        # Remove the folder and all its contents recursively
        rm -rf "$FOLDER"
        
        # Check if the removal was successful
        if [ ! -d "$FOLDER" ]; then
            echo "Successfully removed $FOLDER directory."
        else
            echo "Error: Failed to remove $FOLDER directory."
            exit 1
        fi
    else
        echo "The $FOLDER directory does not exist in the current location."
    fi
done

# Now, remove files
echo -e "\nProcessing files..."
for FILE in "${TARGET_FILES[@]}"; do
    # Check if the file exists
    if [ -f "$FILE" ]; then
        echo "Found $FILE file. Removing..."
        
        # Remove the file
        rm -f "$FILE"
        
        # Check if the removal was successful
        if [ ! -f "$FILE" ]; then
            echo "Successfully removed $FILE file."
        else
            echo "Error: Failed to remove $FILE file."
            exit 1
        fi
    else
        echo "The $FILE file does not exist in the current location."
    fi
done

echo -e "\nAll specified folders and files have been processed."
exit 0