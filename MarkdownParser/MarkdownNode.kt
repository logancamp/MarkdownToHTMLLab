class MarkdownNode(private var type: Int, text: String) : Node(text) {

    override fun toHTML() : String {
        var line = ""

        when (this.type) {
            1 -> {
                line = "<h1> $text </h1>"
            }
            2 -> {
                line = "<h2> $text </h2>"
            }
            3 -> {
                line = "<h3> $text </h3>"
            }
            4 -> {
                line = "<b> $text </b>"
            }
            5 -> {
                line = "<i> $text </i>"
            }
            6 -> {
                line = "<blockquote> $text </blockquote>"
            }
            7 -> {
                line = "<hr>"
            }
            8 -> {
                line = text
            }
        }

        return line
    }
}