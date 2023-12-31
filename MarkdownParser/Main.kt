import java.io.BufferedWriter
import java.io.FileWriter

fun main (args: Array<String>) {
    val markDownParser = MarkdownParser()
    val lines = markDownParser.parseCode("src/main/kotlin/mkTest.md")
    val writer = BufferedWriter(FileWriter("src/main/kotlin/mkParsed.html"))

    for (thing in lines) {
        val line = thing.toHTML()
        writer.write(line)
        writer.newLine()
    }
    writer.flush()
}