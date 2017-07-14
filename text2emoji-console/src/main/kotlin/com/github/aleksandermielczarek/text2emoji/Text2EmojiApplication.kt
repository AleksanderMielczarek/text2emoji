package com.github.aleksandermielczarek.text2emoji

import com.beust.jcommander.JCommander

/**
 * Created by Aleksander Mielczarek on 08.07.2017.
 */

class Text2EmojiApplication(val text2Emoji: Text2Emoji) {

    fun run(args: Array<String>) {
        val arguments = parseArguments(args)
        val emojis = text2Emoji.text2emoji(arguments.text,
                arguments.textEmoji,
                arguments.emptyEmoji,
                arguments.separator(),
                arguments.widthLimit)
        printEmojis(emojis)
    }

    private fun parseArguments(args: Array<String>): Arguments {
        val arguments = Arguments()
        val jCommander = JCommander.newBuilder()
                .addObject(arguments)
                .programName("Text2Emoji")
                .build()

        parse(jCommander, args)
        showHelp(arguments, jCommander)

        return arguments
    }

    private fun showHelp(arguments: Arguments, jCommander: JCommander) {
        if (arguments.help) {
            jCommander.usage()
            System.exit(0)
        }
    }

    private fun parse(jCommander: JCommander, args: Array<String>) {
        try {
            jCommander.parse(*args)
        } catch(e: Exception) {
            jCommander.usage()
            System.exit(1)
        }
    }

    private fun printEmojis(emojis: List<String>) {
        emojis.forEach { println(it) }
    }

}

fun main(args: Array<String>) {
    val text2Emoji = Text2Emoji()
    val app = Text2EmojiApplication(text2Emoji)
    app.run(args)
}
