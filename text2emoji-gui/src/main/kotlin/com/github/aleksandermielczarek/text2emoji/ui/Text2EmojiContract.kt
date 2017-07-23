package com.github.aleksandermielczarek.text2emoji.ui

import javafx.beans.property.BooleanProperty
import javafx.beans.property.IntegerProperty
import javafx.beans.property.StringProperty

/**
 * Created by Aleksander Mielczarek on 22.07.2017.
 */
interface Text2EmojiUseCase {

    fun text2emoji(text: String, textEmoji: String, emptyEmoji: String, separatorEnabled: Boolean, separator: String, widthEnabled: Boolean, width: Int): String

}

interface CopyToClipboardUseCase {

    fun copyToClipboard(emojis: String)
}

interface Presenter {

    val text: StringProperty
    val textEmoji: StringProperty
    val emptyEmoji: StringProperty
    val separatorEnabled: BooleanProperty
    val separator: StringProperty
    val widthEnabled: BooleanProperty
    val width: IntegerProperty
    val emojis: StringProperty

    fun observeText2emoji()

    fun copyToClipboard()

    fun openAboutDialog()
}

interface Router {

    fun openAboutDialog()
}