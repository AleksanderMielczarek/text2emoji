package com.github.aleksandermielczarek.text2emoji

import com.beust.jcommander.Parameter

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
            description = "Separator between emojis",
            required = true)
    lateinit var emojiSeparator: String

    @Parameter(names = arrayOf("--width", "-w"),
            description = "Width limit of single line. 0 for unlimited")
    var widthLimit = Text2Emoji.WIDTH_NO_LIMIT

    @Parameter(names = arrayOf("--help", "-h"), help = true)
    var help: Boolean = false

}