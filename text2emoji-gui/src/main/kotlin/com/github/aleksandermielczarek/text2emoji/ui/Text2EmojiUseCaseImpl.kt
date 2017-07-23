package com.github.aleksandermielczarek.text2emoji.ui

import com.github.aleksandermielczarek.text2emoji.Text2Emoji
import javax.inject.Inject

class Text2EmojiUseCaseImpl @Inject constructor(val text2Emoji: Text2Emoji) : Text2EmojiUseCase {

    override fun text2emoji(text: String, textEmoji: String, emptyEmoji: String, separatorEnabled: Boolean, separator: String, widthEnabled: Boolean, width: Int): String {
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