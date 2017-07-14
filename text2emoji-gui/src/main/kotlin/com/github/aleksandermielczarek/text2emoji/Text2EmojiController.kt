package com.github.aleksandermielczarek.text2emoji

/**
 * Created by Aleksander Mielczarek on 14.07.2017.
 */
import io.reactivex.Observable
import io.reactivex.functions.Function7
import io.reactivex.rxjavafx.observables.JavaFxObservable
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.input.Clipboard
import javafx.scene.input.ClipboardContent


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
        Observable.combineLatest(text.values(),
                textEmoji.values(),
                emptyEmoji.values(),
                separator.disables(),
                separator.values(),
                width.disables(),
                width.values(),
                Function7 { text: String, textEmoji: String, emptyEmoji: String, separatorDisabled: Boolean, separator: String, widthDisabled: Boolean, width: Int ->
                    text2emoji(text, textEmoji, emptyEmoji, separatorDisabled, separator, widthDisabled, width)
                })
                .subscribe { emojis.text = it }
    }

    private fun text2emoji(text: String, textEmoji: String, emptyEmoji: String, separatorDisabled: Boolean, separator: String, widthDisabled: Boolean, width: Int): String {
        val currentWidth = resolveWidth(width, widthDisabled)
        val currentSeparator = resolveSeparator(separator, separatorDisabled)
        val emojis = text2Emoji.text2emoji(text, textEmoji, emptyEmoji, currentSeparator, currentWidth)
        return emojis.joinToString(System.lineSeparator())
    }

    private fun resolveSeparator(separator: String, separatorDisabled: Boolean): String {
        var currentSeparator = separator
        if (separatorDisabled) {
            currentSeparator = ""
        }
        return currentSeparator
    }

    private fun resolveWidth(width: Int, widthDisabled: Boolean): Int {
        var currentWidth = width
        if (widthDisabled) {
            currentWidth = Text2Emoji.WIDTH_NO_LIMIT
        }
        return currentWidth
    }

    private fun TextField.values(): Observable<String> {
        return JavaFxObservable.valuesOf(this.textProperty())
    }

    private fun TextField.disables(): Observable<Boolean> {
        return JavaFxObservable.valuesOf(this.disableProperty())
    }

    private fun Spinner<Int>.values(): Observable<Int> {
        return JavaFxObservable.valuesOf(this.valueProperty())
    }

    private fun Spinner<Int>.disables(): Observable<Boolean> {
        return JavaFxObservable.valuesOf(this.disableProperty())
    }
}
