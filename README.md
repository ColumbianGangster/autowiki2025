# Lua to JSON Converter (Kotlin Script)

This project provides a Kotlin script that converts Lua tables into JSON format. The script reads Lua files from a directory, processes them, and generates corresponding JSON files in a separate output folder.

## Features

- Converts Lua tables from Lua scripts to JSON format.
- Supports nested Lua tables.
- Outputs the JSON files to a `json` directory (instead of overwriting the Lua files).
- Pretty prints the JSON with indentation.

## Requirements

To run the script, you'll need:
- **Kotlin Compiler** (`kotlinc`)
- **Java Compiler** (`java`)
- **JSON Library** for Kotlin (like `org.json`)

Additionally, the script uses an external JAR files:
- `gson-1.12.1.jar` (for JSON conversion)

Make sure these JAR files are available in your project directory.

## Setup

### 1. Install Kotlin

Make sure you have Kotlin installed. If not, you can install Kotlin using SDKMAN or download it from the [Kotlin website](https://kotlinlang.org/).

To check if Kotlin is installed, run:
```bash
kotlinc -version
```

### 2. Add Dependencies

Download the following JAR files and place them in the project directory:
- `luaj-jse-3.0.1.jar`: [Download from here](https://github.com/luaj/luaj)
- `json-20210307.jar`: [Download from here](https://mvnrepository.com/artifact/org.json/json)

### 3. Kotlin Script

Place the Kotlin script `dowpro_parser.kts` (the Lua-to-JSON conversion script) in the project directory.

### 4. Bash Script (Optional)

If you want to easily run the script, you can use the provided Bash script `run_parser.sh`.

## Usage

### Running the Script Manually

You can manually compile and run the Kotlin script with the necessary dependencies using the following command:

```bash
kotlinc -cp "luaj-jse-3.0.1.jar:json-20210307.jar" -script dowpro_parser.kts
```

This will process all Lua files in the current directory, convert them to JSON, and save the results in the `json` directory.

### Using the Bash Script

To simplify running the script, you can use the `autowiki.sh` Bash script.

1. Make the script executable:
   ```bash
   chmod +x autowiki.sh
   ```

2. Run the script:
   ```bash
   ./autowiki.sh
   ```

This will automatically check for the required JAR files, compile the Kotlin script, and execute it.

### Output

The output JSON files will be saved in the `json` directory within the project folder. The JSON files will have the same name as the original Lua files but with the `.json` extension.

Example output structure:

```
/lua
  ├── script1.lua
  ├── script2.lua
  ├── script3.lua
  └── /json
        ├── script1.json
        ├── script2.json
        └── script3.json
```

### Configuration

You can change the Lua input directory path and customize the JSON output directory in the `dowpro_parser.kts` script or by modifying the Bash script.

## Troubleshooting

- **Missing Dependencies**: Ensure the required JAR files (`luaj-jse-3.0.1.jar` and `json-20210307.jar`) are present in the project directory.
- **Invalid Directory**: If the script fails due to invalid paths, ensure that the directory paths are correctly specified in both the `dowpro_parser.kts` script and the `run_parser.sh` script.
