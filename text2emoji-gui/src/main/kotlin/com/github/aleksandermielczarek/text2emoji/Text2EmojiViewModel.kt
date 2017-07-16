package com.github.aleksandermielczarek.text2emoji

import com.github.thomasnield.rxkotlinfx.toBinding
import com.github.thomasnield.rxkotlinfx.toObservable
import io.reactivex.rxkotlin.Observables
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import javafx.scene.input.Clipboard
import javafx.scene.input.ClipboardContent
import javax.inject.Inject

/**
 * Created by Aleksander Mielczarek on 16.07.2017.
 */
class Text2EmojiViewModel @Inject constructor(val text2Emoji: Text2Emoji) {

    companion object {
        val MAX_WIDTH = 100
    }

    val text = SimpleStringProperty()
    val textEmoji = SimpleStringProperty()
    val emptyEmoji = SimpleStringProperty()
    val separatorEnabled = SimpleBooleanProperty()
    val separator = SimpleStringProperty()
    val widthEnabled = SimpleBooleanProperty()
    val width = SimpleIntegerProperty()
    val emojis = SimpleStringProperty()

    fun copyToClipboard() {
        val clipboard = Clipboard.getSystemClipboard()
        val clipboardContent = ClipboardContent()
        clipboardContent.putString(emojis.value)
        clipboard.setContent(clipboardContent)
    }

    fun observeText2emoji() {
        emojis.bind(Observables.combineLatest(text.toObservable(),
                textEmoji.toObservable(),
                emptyEmoji.toObservable(),
                separatorEnabled.toObservable(),
                separator.toObservable(),
                widthEnabled.toObservable(),
                width.toObservable().map { it.toInt() },
                this::text2emoji)
                .toBinding())
    }

    private fun text2emoji(text: String, textEmoji: String, emptyEmoji: String, separatorEnabled: Boolean, separator: String, widthEnabled: Boolean, width: Int): String {
        val currentWidth = resolveWidth(width, widthEnabled)
        val currentSeparator = resolveSeparator(separator, separatorEnabled)
        return text2Emoji.text2emoji(text, textEmoji, emptyEmoji, currentSeparator, currentWidth)
    }

    private fun resolveSeparator(separator: String, separatorEnabled: Boolean): String {
        if (separatorEnabled) {
            return separator
        } else {
            return ""
        }
    }

    private fun resolveWidth(width: Int, widthEnabled: Boolean): Int {
        if (widthEnabled) {
            return width
        } else {
            return Text2Emoji.WIDTH_NO_LIMIT
        }
    }

}