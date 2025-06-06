import java.io.File
import java.math.BigDecimal
import java.math.RoundingMode

fun printClickableLink(text: String, url: String) {
    val hyperlink = "\u001B]8;;$url\u0007$text\u001B]8;;\u0007"
    println(hyperlink)
}

fun main() {
    val inputDir = File("docs")   // Change this
    val outputDir = File("docs") // Change this
    val indexFile = File(outputDir, "index.html")

   val htmlContent = buildString {
        appendLine("<!DOCTYPE html>")
        appendLine("<html lang=\"en\">")
        appendLine("<head>")
        appendLine("    <meta charset=\"UTF-8\">")
        appendLine("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">")
        appendLine("    <title>Index of Files</title>")
        appendLine("    <style>")
        appendLine("""
            body {
                margin: 0;
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                display: flex;
                height: 100vh;
                background-color: #f4f4f4;
                color: #333;
            }
            #sidebar {
                width: 300px;
                background-color: #fafafa;
                overflow-y: auto;
                padding: 20px;
                border-right: 1px solid #ccc;
            }
            #content {
                flex: 1;
                overflow: hidden;
            }
            iframe {
                width: 100%;
                height: 100%;
                border: none;
            }
            h1 {
                margin-top: 0;
                font-size: 1.5rem;
            }
            ul {
                list-style-type: none;
                padding-left: 10px;
            }
            li {
                margin: 5px 0;
                line-height: 1.6;
            }
            a {
                text-decoration: none;
                color: #007BFF;
                cursor: pointer;
            }
            a:hover {
                text-decoration: underline;
            }
            .folder {
                font-weight: bold;
                cursor: pointer;
            }
            .hidden {
                display: none;
            }
            .icon {
                margin-right: 8px;
            }
            .active {
                font-weight: bold;
                color: #d63384;
            }
            @media (prefers-color-scheme: dark) {
                body {
                    background-color: #1e1e1e;
                    color: #cfcfcf;
                }
                #sidebar {
                    background-color: #2a2a2a;
                    border-color: #444;
                }
                a {
                    color: #4ea1ff;
                }
                a.active {
                    color: #ff79c6;
                }
            }
        """.trimIndent())
        appendLine("    </style>")
        appendLine("</head>")
        appendLine("<body>")
        appendLine("    <div id=\"sidebar\">")
        appendLine("        <h1>Index of Files</h1>")
        appendLine("        <ul>")

        generateLinks(inputDir, inputDir, this)

        appendLine("        </ul>")
        appendLine("    </div>")
        appendLine("    <div id=\"content\">")
        appendLine("        <iframe id=\"preview\" src=\"\"></iframe>")
        appendLine("    </div>")

        appendLine("""
            <script>
                document.querySelectorAll('.folder').forEach(folder => {
                    folder.addEventListener('click', () => {
                        const childList = folder.nextElementSibling;
                        if (childList) {
                            childList.classList.toggle('hidden');
                        }
                    });
                });

                document.querySelectorAll('a.file-link').forEach(link => {
                    link.addEventListener('click', (e) => {
                        e.preventDefault();
                        const iframe = document.getElementById('preview');
                        iframe.src = link.getAttribute('href');
                        
                        // Remove active from others
                        document.querySelectorAll('a.file-link').forEach(l => l.classList.remove('active'));
                        // Add active to clicked link
                        link.classList.add('active');
                    });
                });
            </script>
        """.trimIndent())
        appendLine("</body>")
        appendLine("</html>")
    }

    indexFile.writeText(htmlContent)

    printClickableLink("Index file generated at: ${indexFile.absolutePath}", indexFile.absolutePath)
}

fun cleanString(input: String): String {

    val stringsToRemove = listOf(
        "_" to " ",
        ".html" to "",
        "chaos" to "",
        "dark eldar" to "",
        "eldar" to "",
        "guard " to "",
        "necron" to "",
        "ork" to "",
        "sisters" to "",
        "space marine" to "",
        "steel legion" to "",
        "tau" to "",
    )

    var cleanedString = input
    for ((oldValue, newValue) in stringsToRemove) {
        cleanedString = cleanedString.replace(oldValue, newValue)
    }

    // Handle number strings that can be converted to actual numbers
    val number = cleanedString.toDoubleOrNull()
    if (number != null) {
        // Check if it's a whole number, and remove decimal places if it's effectively an integer
        val roundedNumber = BigDecimal(number.toString()).setScale(2, RoundingMode.HALF_UP).toDouble()
        return if (roundedNumber == roundedNumber.toInt().toDouble()) {
            // Return the rounded number as an integer
            roundedNumber.toInt().toString()
        } else {
            // Return the number as a string with 2 decimal places
            roundedNumber.toString()
        }
    }

    return cleanedString
}

// Recursively generate links
fun generateLinks(baseDir: File, currentDir: File, builder: StringBuilder) {
    val ignoreList = listOf(
        ".DS_Store",
        "Thumbs.db"
    )

    currentDir.listFiles()?.sortedBy { it.name.lowercase() }?.forEach { file ->
        val relativePath = baseDir.toPath().relativize(file.toPath()).toString().replace("\\", "/")
        if (file.name in ignoreList) {
            println("Ignoring: $relativePath")
            return@forEach
        }
        if (file.isDirectory) {
            builder.appendLine("<li><span class=\"folder\">📁 ${file.name}/</span>")
            builder.appendLine("<ul class=\"hidden\">")
            generateLinks(baseDir, file, builder)
            builder.appendLine("</ul></li>")
        } else {
            builder.appendLine("<li>📄 <a href=\"$relativePath\" class=\"file-link\">${cleanString(file.name)}</a></li>")
        }
    }
}

main()
