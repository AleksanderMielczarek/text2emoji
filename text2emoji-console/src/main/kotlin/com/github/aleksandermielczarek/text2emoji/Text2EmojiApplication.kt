package com.github.aleksandermielczarek.text2emoji

import com.beust.jcommander.JCommander
import com.google.inject.Guice
import javax.inject.Inject

/**
 * Created by Aleksander Mielczarek on 08.07.2017.
 */
class Text2EmojiApplication @Inject constructor(val text2Emoji: Text2Emoji) {

    fun run(args: Array<String>) {
        val arguments = parseArguments(args)
        val emojis = text2Emoji.text2emoji(arguments.text,
                arguments.textEmoji,
                arguments.emptyEmoji,
                arguments.separator(),
                arguments.widthLimit)
        println(emojis)
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

}

fun main(args: Array<String>) {
    val injector = Guice.createInjector()
    val app = injector.getInstance(Text2EmojiApplication::class.java)
    app.run(args)
}
