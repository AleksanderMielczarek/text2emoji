package com.github.aleksandermielczarek.text2emoji

import com.beust.jcommander.Parameter
import java.util.*

/**
 * Created by Aleksander Mielczarek on 09.07.2017.
 */
class Arguments {

    @Parameter(names = arrayOf("--text", "-t"),
            description = "Text that will be changed to emoji",
            required = true)
    lateinit var text: String

    @Parameter(names = arrayOf("--text-emoji", "-te"),
            description = "Emoji that will be displayed instead of text",
            required = true)
    lateinit var textEmoji: String

    @Parameter(names = arrayOf("--empty-emoji", "-ee"),
            description = "Emoji that will be displayed instead of space etc.",
            required = true)
    lateinit var emptyEmoji: String

    @Parameter(names = arrayOf("--separator", "-s"),
            description = "Separator between emojis. Default is blank.")
    var emojiSeparator: String = ""

    @Parameter(names = arrayOf("--space-separator", "-ss"),
            description = "Number of spaces between emojis. Default is 0.")
    var spaceSeparators: Int = 0

    @Parameter(names = arrayOf("--width", "-w"),
            description = "Width limit of single line. Default is unlimited. 0 for unlimited")
    var widthLimit = Text2Emoji.WIDTH_NO_LIMIT

    @Parameter(names = arrayOf("--help", "-h"), help = true)
    var help: Boolean = false

    fun separator(): String {
        if (!emojiSeparator.isEmpty()) {
            return emojiSeparator
        }
        if (spaceSeparators < 0) {
            return emojiSeparator
        }
        return Collections.nCopies(spaceSeparators, " ").joinToString("")
    }
}