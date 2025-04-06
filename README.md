# AutoWiki 2025 - Lua to JSON Converter

**AutoWiki 2025** provides a Kotlin-based solution for converting Lua tables into JSON format. This project processes Lua files, converts their contents into JSON, and outputs them into separate files, making it ideal for handling large Lua datasets.

## Features
- Converts Lua tables into JSON format.
- Supports nested Lua tables.
- Outputs formatted JSON files to avoid overwriting the original Lua files.
- Pretty prints the JSON with indentation for readability.

## Setup

### Prerequisites
- Kotlin Compiler (`kotlinc`)
- Java (`java`)
- Gson Library (`gson-2.12.1.jar`)

### Installation
1. Install **Kotlin** and ensure it's available:
    ```bash
    kotlinc -version
    ```

2. Add dependencies:
    - Download `gson-2.12.1.jar`
    - Place them in the project directory.

3. Place the Kotlin script (`dowpro_parser.kts`) in the directory.

4. Optionally, use the provided Bash script (`run_parser.sh`) for easier execution.

## Usage

### Using the Bash Script
1. Make the script executable:
    ```bash
    chmod +x autowiki.sh
    ```

2. Run the script:
    ```bash
    ./autowiki.sh
    ```

This will handle dependencies, compile, and execute the conversion process.

### Output
Converted JSON files will be placed in the `json` directory with the same name as the original Lua files.

Example structure:
```
/lua
 ├── script1.lua
 ├── script2.lua
 └── /json
      ├── script1.json
      ├── script2.json
```

## Troubleshooting
- Ensure required JAR files are in the project directory.
- Verify directory paths in scripts.
