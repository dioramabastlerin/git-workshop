package impl

enum class LogOutputFormat { HTML, MARKDOWN }

data class LogBuilderOptions(
        val outputFormat: LogOutputFormat = LogOutputFormat.HTML,
        val createFullLog: Boolean = false
)
