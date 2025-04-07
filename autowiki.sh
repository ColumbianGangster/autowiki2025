#!/bin/bash

# Define the paths to your Kotlin script and JAR files
JAR_LUAJ="luaj-jse-3.0.1.jar"
JAR_JSON="json-20210307.jar"
JAR_GSON="gson-2.12.1.jar"
KOTLIN_SCRIPT="dowpro_parser.kts"
KOTLIN_SCRIPT2="dowpro_json_cleaner.kts"
KOTLIN_SCRIPT3="dowpro_html.kts"
KOTLIN_SCRIPT4="dowpro_entity_html.kts"
KOTLIN_SCRIPT5="dowpro_index.kts"

# Check if the necessary JAR files exist
if [[ ! -f "$JAR_LUAJ" ]]; then
    echo "Error: $JAR_LUAJ not found!"
    exit 1
fi

if [[ ! -f "$JAR_JSON" ]]; then
    echo "Error: $JAR_JSON not found!"
    exit 1
fi

if [[ ! -f "$JAR_GSON" ]]; then
    echo "Error: $JAR_GSON not found!"
    exit 1
fi

rm -r docs
rm -r rawjson
# Run the Kotlin script with the required classpath
kotlinc -cp "$JAR_LUAJ:$JAR_JSON" -script "$KOTLIN_SCRIPT"
kotlinc -cp "$JAR_GSON" -script "$KOTLIN_SCRIPT2"
kotlinc -cp "$JAR_GSON" -script "$KOTLIN_SCRIPT3"
kotlinc -cp "$JAR_GSON" -script "$KOTLIN_SCRIPT4"
kotlinc -script "$KOTLIN_SCRIPT5"

# Exit with the status of the last command
exit $?
