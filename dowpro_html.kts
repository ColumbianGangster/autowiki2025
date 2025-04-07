import com.google.gson.*
import java.io.File
import java.nio.file.Paths

fun main() {
    val inputDir = File("cleanedjson") // Directory containing JSON files
    val outputDir = File("html") // Directory to save HTML files

    processDirectory(inputDir, outputDir)
    println("HTML generation completed.")
}

fun processDirectory(inputDir: File, outputDir: File) {
    if (!inputDir.exists()) {
        println("Input directory does not exist.")
        return
    }
    
    inputDir.walkTopDown().forEach { file ->
        if (file.isDirectory) {
            val outputSubDir = File(outputDir, file.relativeTo(inputDir).path)
            outputSubDir.mkdirs()
        } else if (file.extension == "json") {
            val relativePath = file.relativeTo(inputDir).path.replace(".json", ".html")
            val outputFile = File(outputDir, relativePath)
            
            val jsonContent = file.readText()
            val jsonElement = JsonParser.parseString(jsonContent)
            val htmlContent = generateHtml(jsonElement, file.nameWithoutExtension)
            
            outputFile.writeText(htmlContent)
            // println("Generated: ${outputFile.absolutePath}")
        }
    }
}

fun generateHtml(jsonElement: JsonElement, title: String): String {
    val bodyContent = buildHtml(jsonElement)
    return """
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>$title</title>
            <style>
                body { font-family: Arial, sans-serif; background-color: #f0f0f5; margin: 0; padding: 20px; color: #333; }
                .container { max-width: 1200px; margin: 0 auto; padding: 20px; background: white; border-radius: 10px; box-shadow: 0px 0px 15px rgba(0,0,0,0.1); }
                h1 { text-align: center; font-size: 2em; margin-bottom: 20px; }
                .json-table { width: 100%; border-collapse: collapse; margin-top: 20px; }
                .json-table th, .json-table td { padding: 12px 15px; border: 1px solid #ddd; text-align: left; }
                .json-table th { background-color: #4CAF50; color: white; font-size: 1.1em; }
                .json-key { font-weight: bold; color: #007BFF; }
                .json-value { color: #4CAF50; }
                .json-value-string { font-style: italic; }
                .json-object, .json-array { padding-left: 20px; margin-top: 10px; }
            </style>
        </head>
        <body>
            <div class="container">
                <h1>$title</h1>
                <div class="json-content">
                    $bodyContent
                </div>
            </div>
        </body>
        </html>
    """.trimIndent()
}

fun buildHtml(element: JsonElement): String {
    return when {
        element.isJsonObject -> {
            val jsonObject = element.asJsonObject
            """<table class="json-table">
                <tr><th>Key</th><th>Value</th></tr>""" +
                jsonObject.entrySet().joinToString("") { (key, value) ->
                    """<tr><td class="json-key">$key</td><td>${buildHtml(value)}</td></tr>"""
                } + "</table>"
        }
        element.isJsonArray -> {
            val jsonArray = element.asJsonArray
            """<table class="json-table">
                <tr><th>Index</th><th>Value</th></tr>""" +
                jsonArray.mapIndexed { index, value ->
                    """<tr><td class="json-key">$index</td><td>${buildHtml(value)}</td></tr>"""
                }.joinToString("") + "</table>"
        }
        element.isJsonPrimitive -> {
            when {
                element.asJsonPrimitive.isString -> """<span class="json-value json-value-string">"${element.asString}"</span>"""
                element.asJsonPrimitive.isNumber -> """<span class="json-value">${element.asNumber}</span>"""
                element.asJsonPrimitive.isBoolean -> """<span class="json-value">${element.asBoolean}</span>"""
                else -> ""
            }
        }
        else -> ""
    }
}

main()