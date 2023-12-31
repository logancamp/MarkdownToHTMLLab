import java.io.BufferedReader
import java.io.FileReader

class MarkdownParser : CodeParser {
    override fun parseCode(filename: String): List<Node> {
        val lines = mutableListOf<Node>()
        val reader = BufferedReader(FileReader(filename))

        var line:String?
        while(reader.readLine().also {line = it} != null) {
            line?.let {
                val htmlLine = regxChecker(it)
                val lineNode = MarkdownNode(htmlLine[0].toInt(), htmlLine[1])
                lines.add(lineNode)
            }
        }
        return lines
    }

    private fun regxChecker(line:String) : List<String> {
        var type = ""
        var htmlText = ""

        if (Regex("(^#?)\\s+(.*)").matches(line)) {
            val result = Regex("(^#?)\\s+(.*)").matchEntire(line)
            result?.let {
                type = "1"
                htmlText = result.groups[2]?.value.toString()
            }

        } else if (Regex("(^##?)\\s+(.*)").matches(line)) {
            val result = Regex("(^##?)\\s+(.*)").matchEntire(line)
            result?.let {
                type = "2"
                htmlText = result.groups[2]?.value.toString()
            }

        } else if (Regex("(^###?)\\s+(.*)").matches(line)) {
            val result = Regex("(^###?)\\s+(.*)").matchEntire(line)
            result?.let {
                type = "3"
                htmlText = result.groups[2]?.value.toString()
            }

        } else if (Regex("(\\*\\*)(.+?)\\*\\*").matches(line)) {
            val result = Regex("(\\*\\*)(.+?)\\*\\*").matchEntire(line)
            result?.let {
                type = "4"
                htmlText = result.groups[2]?.value.toString()
            }

        } else if (Regex("(\\*)(.+?)\\*").matches(line)) {
            val result = Regex("(\\*)(.+?)\\*").matchEntire(line)
            result?.let {
                type = "5"
                htmlText = result.groups[2]?.value.toString()
            }

        } else if (Regex("(>?)\\s+(.*)").matches(line)) {
            val result = Regex("(>?)\\s+(.*)").matchEntire(line)
            result?.let {
                type = "6"
                htmlText = result.groups[2]?.value.toString()
            }

        } else if (Regex("^---$").matches(line)) {
            return listOf("7", "")
        } else {
            return listOf("8", line)
        }

        return listOf(type, htmlText)
    }
}
