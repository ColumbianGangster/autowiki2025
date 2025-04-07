import java.io.File
import org.json.JSONObject
import java.nio.file.Paths

fun main() {
    // Top-level directory path to start searching for Lua files
    val directoryPath = "attrib"  // Replace with your Lua directory path
    val outputDirectory = "rawjson"  // The separate output directory

    // Start recursive search and parsing
    val luaFiles = getLuaFiles(directoryPath)
    luaFiles.forEach { luaFile ->
        try {
            val luaContent = luaFile.readText()
            val json = parseLuaToJson(luaContent)
            
            // Generate the output JSON file path in the 'output' directory, preserving the folder structure
            val jsonOutputFilePath = generateJsonOutputFilePath(directoryPath, luaFile, outputDirectory)
            
            // Create necessary directories in the output folder if they don't exist
            val outputFile = File(jsonOutputFilePath)
            outputFile.parentFile.mkdirs()
            
            // Write the JSON to the output file
            outputFile.writeText(json.toString(4))  // Pretty print the JSON
            println("Created JSON file: ${outputFile.absolutePath}")
        } catch (e: Exception) {
            println("Error processing file ${luaFile.absolutePath}: ${e.message}")
        }
    }
}

// Recursively get all Lua files from a directory and its subdirectories
fun getLuaFiles(directoryPath: String): List<File> {
    val filesToExclude = listOf(
        "_dxp3_", "_hg_", "_sp_", "_dxp3.", "_hg.", "_sp.", "nis.",
    )

    val directoriesToInclude = listOf(
        "abilities", "addons", "ebps", "races",
        "chaos", "dark_eldar", "eldar", "guard", "necron",
        "ork", "sisters", "space_marine", "steel_legion", "tau", "tyranid",
        "structures", "troops", "modifiers", "requirements", "research", "sbps"
    )

    val directoriesToExclude = listOf(
        "environment",
    )

    val directory = File(directoryPath)
    val luaFiles = mutableListOf<File>()

    if (directory.exists() && directory.isDirectory) {
        directory.walkTopDown().forEach { file ->
            if (
                file.isFile &&
                file.extension == "lua" &&
                filesToExclude.none { file.name.contains(it) } &&
                file.parentFile?.name in directoriesToInclude && 
                directoriesToExclude.none { includeDir ->
                    file.relativeTo(directory).path.contains(includeDir)
                }
            ) {
                luaFiles.add(file)
            }
        }
    }
    return luaFiles
}

// Function to parse Lua content into a nested JSON structure
fun parseLuaToJson(luaContent: String): JSONObject {
    val jsonObject = JSONObject()

    // Regex patterns for key-value pairs and reference tables
    val keyValuePattern = """\s*([\w\[\]\"']+)\s*=\s*(.*)""".toRegex()
    val inheritPattern = """\s*([\w\[\]\"']+)\s*=\s*Inherit\s*\(\[\[([^\]]+)\]\]\)""".toRegex()
    val referencePattern = """\s*([\w\[\]\"']+)\s*=\s*Reference\s*\(\[\[([^\]]+)\]\]\)""".toRegex()

    // Split the Lua content by lines
    val lines = luaContent.lines()

    // Variable to track the current object we're building
    var currentObject = jsonObject

    // Parse the Lua content line by line
    for (line in lines) {
        val trimmedLine = line.trim()
        // Skip empty lines and comments
        if (trimmedLine.isEmpty() || trimmedLine.startsWith("--")) continue

        // If the line defines a key-value pair
        if (trimmedLine.matches(keyValuePattern)) {
            val match = keyValuePattern.find(trimmedLine)!!
            val key = match.groupValues[1].trim()
            val value = match.groupValues[2].trim()

            // Split the key into parts (handling nested keys)
            val keyParts = splitKey(key)

            // Navigate to the correct location in the nested structure
            var nestedObject = currentObject
            for (part in keyParts.dropLast(1)) {
                nestedObject = nestedObject.optJSONObject(part) ?: JSONObject().also {
                    nestedObject.put(part, it)
                }
            }

            // Handle references and other values
            if (value.startsWith("Reference")) {
                // Handle references (nested objects)
                val refName = value.removePrefix("Reference([[").removeSuffix("]])")
                nestedObject.put(keyParts.last(), JSONObject().apply { put("reference", refName) })
            } else if (value.startsWith("Inherit")) {
            } else if (value.equals("nil", ignoreCase = true) || value == "\"nil\"") {
                // println(line)
                // Handle "nil" values and convert them to null
                // nestedObject.put(keyParts.last(), JSONObject.NULL)
            } else {
                // Handle regular key-value assignment
                nestedObject.put(keyParts.last(), value)
            }
        } else if (trimmedLine.matches(inheritPattern)) {
            // Handle Inherit pattern (recursive nesting)
            val match = inheritPattern.find(trimmedLine)!!
            val key = match.groupValues[1].trim()
            val refTable = match.groupValues[2].trim()

            val nestedJson = parseLuaTable(refTable)  // Recursively parse the inherited table
            currentObject.put(key, nestedJson)
        } else if (trimmedLine.matches(referencePattern)) {
            // Handle Reference pattern (nested object references)
            val match = referencePattern.find(trimmedLine)!!
            val key = match.groupValues[1].trim()
            val refName = match.groupValues[2].trim()

            currentObject.put(key, JSONObject().apply { put("reference", refName) })
        }
    }

    return jsonObject
}

// Function to split the key into parts (to create the nested structure)
fun splitKey(key: String): List<String> {
    val regex = """\["([^"]+)"\]""".toRegex()
    val matches = regex.findAll(key)

    return matches.map { it.groupValues[1] }.toList()
}

// Recursive function to parse an inherited table (for deeper nested references)
fun parseLuaTable(tableName: String): JSONObject {
    // For simplicity, assume we can extract and handle the table directly by its name
    // In a real-world scenario, you would handle more complex table references here.
    // This is a placeholder function and could be expanded to load or parse external tables.

    val nestedJson = JSONObject()
    // We simulate this by referencing the table name directly for simplicity
    nestedJson.put("inherit", tableName)

    return nestedJson
}

// Generate the output file path for the corresponding JSON file inside the 'output' directory
fun generateJsonOutputFilePath(baseDir: String, luaFile: File, outputDir: String): String {
    // Get the relative path of the Lua file from the base directory
    val relativePath = luaFile.toRelativeString(File(baseDir))

    // Create the full path by replacing the ".lua" extension with ".json" inside the 'output' directory
    return Paths.get(outputDir, relativePath.replace(".lua", ".json")).toString()
}

main()
