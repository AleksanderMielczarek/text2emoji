package com.github.aleksandermielczarek.text2emoji

import com.github.thomasnield.rxkotlinfx.textValues
import com.github.thomasnield.rxkotlinfx.toBinding
import com.github.thomasnield.rxkotlinfx.toObservable
import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables
import javafx.beans.property.Property
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.input.Clipboard
import javafx.scene.input.ClipboardContent

/**
 * Created by Aleksander Mielczarek on 14.07.2017.
 */
class Text2EmojiController {

    companion object {
        val MAX_WIDTH = 100
    }

    @FXML
    private lateinit var text: TextField

    @FXML
    private lateinit var textEmoji: TextField

    @FXML
    private lateinit var emptyEmoji: TextField

    @FXML
    private lateinit var separator: TextField

    @FXML
    private lateinit var separatorEnabled: CheckBox

    @FXML
    private lateinit var widthEnabled: CheckBox

    @FXML
    private lateinit var width: Spinner<Int>

    @FXML
    private lateinit var emojis: TextArea

    private val text2Emoji = Text2Emoji()

    @FXML
    fun initialize() {
        initSpinner()
        bindWidgets()
        observeText2emoji()
    }

    @FXML
    fun copyToClipboard(event: ActionEvent) {
        val clipboard = Clipboard.getSystemClipboard()
        val clipboardContent = ClipboardContent()
        clipboardContent.putString(emojis.text)
        clipboard.setContent(clipboardContent)
    }

    private fun initSpinner() {
        val spinnerValueFactory = SpinnerValueFactory.IntegerSpinnerValueFactory(Symbol.width, MAX_WIDTH)
        width.valueFactory = spinnerValueFactory
    }

    private fun bindWidgets() {
        width.disableProperty().bind(widthEnabled.selectedProperty().not())
        separator.disableProperty().bind(separatorEnabled.selectedProperty().not())
    }

    private fun observeText2emoji() {
        Observables.combineLatest(text.textValues,
                textEmoji.textValues,
                emptyEmoji.textValues,
                separatorEnabled.values(),
                separator.textValues,
                widthEnabled.values(),
                width.values(),
                this::text2emoji)
                .bind(emojis.textProperty())
    }

    private fun text2emoji(text: String, textEmoji: String, emptyEmoji: String, separatorEnabled: Boolean, separator: String, widthEnabled: Boolean, width: Int): String {
        val currentWidth = resolveWidth(width, widthEnabled)
        val currentSeparator = resolveSeparator(separator, separatorEnabled)
        val emojis = text2Emoji.text2emoji(text, textEmoji, emptyEmoji, currentSeparator, currentWidth)
        return emojis.joinToString(System.lineSeparator())
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

    private fun CheckBox.values(): Observable<Boolean> {
        return selectedProperty().toObservable()
    }

    private fun <T> Spinner<T>.values(): Observable<T> {
        return valueProperty().toObservable()
    }

    private fun <T> Observable<T>.bind(property: Property<T>) {
        property.bind(this.toBinding())
    }

}
