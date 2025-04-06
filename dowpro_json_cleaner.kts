import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonArray
import java.io.File
import java.math.BigDecimal
import java.math.RoundingMode

fun main() {
    val inputDirPath = "rawjson" // The input directory containing JSON files
    val outputDirPath = "cleanedjson" // The output directory where cleaned files will be saved

    // Process the entire input directory recursively
    processDirectory(File(inputDirPath), File(outputDirPath))
    println("All JSON files have been cleaned and saved.")
}

// Recursively process a directory
fun processDirectory(inputDir: File, outputDir: File) {
    if (!inputDir.exists()) {
        println("Input directory does not exist.")
        return
    }

    // Ensure the output directory exists
    if (!outputDir.exists()) {
        outputDir.mkdirs()
    }

    // Process each file and subdirectory in the input directory
    inputDir.walkTopDown().forEach { file ->
        if (file.isDirectory) {
            // Create corresponding directory in the output folder
            val relativePath = file.relativeTo(inputDir).path
            val outputSubDir = File(outputDir, relativePath)
            if (!outputSubDir.exists()) {
                outputSubDir.mkdirs()
            }
        } else if (file.extension == "json") {
            // Read the input JSON file
            val jsonString = file.readText()

            // Parse the JSON string into a JsonElement
            val gson = Gson()
            val jsonElement = gson.fromJson(jsonString, JsonElement::class.java)

            // Clean the JSON
            val cleanedJson = cleanJson(jsonElement)

            // Convert the cleaned JSON back to a string with pretty printing
            val gsonPretty = GsonBuilder().setPrettyPrinting().create()
            val cleanedJsonString = gsonPretty.toJson(cleanedJson)

            // Write the cleaned JSON to the corresponding output file
            val relativePath = file.relativeTo(inputDir).path
            val outputFile = File(outputDir, relativePath)
            outputFile.writeText(cleanedJsonString)

            println("Cleaned: ${file.absolutePath} -> ${outputFile.absolutePath}")
        }
    }
}

// Function to clean the JSON object based on the requirements
fun cleanJson(element: JsonElement): JsonElement {
    return when {
        element.isJsonObject -> {
            val jsonObject = element.asJsonObject
            val cleanedJsonObject = JsonObject()

            for ((key, value) in jsonObject.entrySet()) {
                val newKey = key.replace("_", " ")
                val cleanedValue = cleanJson(value)
                cleanedJsonObject.add(newKey, cleanedValue)
            }
            cleanedJsonObject
        }
        element.isJsonArray -> {
            val jsonArray = element.asJsonArray
            val cleanedJsonArray = JsonArray()

            for (item in jsonArray) {
                cleanedJsonArray.add(cleanJson(item))
            }
            cleanedJsonArray
        }
        element.isJsonPrimitive -> {
            val primitive = element.asJsonPrimitive
            when {
                primitive.isNumber -> {
                    val number = primitive.asNumber
                    if (number is Double || number is Float) {
                        // Round up numbers to 2 decimal places
                        val roundedNumber = BigDecimal(number.toString()).setScale(2, RoundingMode.HALF_UP).toDouble()
                        // Check if the rounded number is effectively an integer
                        return if (roundedNumber == roundedNumber.toInt().toDouble()) {
                            Gson().toJsonTree(roundedNumber.toInt()) // Remove decimal places for whole numbers
                        } else {
                            Gson().toJsonTree(roundedNumber)
                        }
                    }
                    return element
                }
                primitive.isString -> {
                    val str = primitive.asString
                    when {
                        str.contains(".lua") -> {
                            return Gson().toJsonTree(str.replace(".lua", ".json"))
                        }
                        str.contains("[[") || str.contains("]]") -> {
                            return Gson().toJsonTree(str.replace("[[", "").replace("]]", ""))
                        }
                        str.toDoubleOrNull() != null -> {
                            // Replace any number-as-string with an actual number
                            return Gson().toJsonTree(str.toDouble())
                        }
                    }
                    return element
                }
                else -> {
                    return element
                }
            }
        }
        else -> element
    }
}

main()
