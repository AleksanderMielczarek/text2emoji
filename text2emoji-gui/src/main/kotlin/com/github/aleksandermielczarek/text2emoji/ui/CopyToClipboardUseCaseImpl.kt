package com.github.aleksandermielczarek.text2emoji.ui

import javafx.scene.input.Clipboard
import javafx.scene.input.ClipboardContent

class CopyToClipboardUseCaseImpl : CopyToClipboardUseCase {

    override fun copyToClipboard(emojis: String) {
        val clipboard = Clipboard.getSystemClipboard()
        val clipboardContent = ClipboardContent()
        clipboardContent.putString(emojis)
        clipboard.setContent(clipboardContent)
    }

}