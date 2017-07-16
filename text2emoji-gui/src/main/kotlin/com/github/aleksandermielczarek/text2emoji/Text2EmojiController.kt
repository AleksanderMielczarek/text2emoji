package com.github.aleksandermielczarek.text2emoji

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.*

/**
 * Created by Aleksander Mielczarek on 14.07.2017.
 */
class Text2EmojiController {

    @FXML
    lateinit var text: TextField

    @FXML
    lateinit var textEmoji: TextField

    @FXML
    lateinit var emptyEmoji: TextField

    @FXML
    lateinit var separatorEnabled: CheckBox

    @FXML
    lateinit var separator: TextField

    @FXML
    lateinit var widthEnabled: CheckBox

    @FXML
    lateinit var width: Spinner<Int>

    @FXML
    lateinit var emojis: TextArea

    private val viewModel = Text2EmojiViewModel()

    @FXML
    fun initialize() {
        initSpinner()
        bindWidgets()
        viewModel.observeText2emoji()
    }

    @FXML
    fun copyToClipboard(event: ActionEvent) {
        viewModel.copyToClipboard()
    }

    private fun initSpinner() {
        val spinnerValueFactory = SpinnerValueFactory.IntegerSpinnerValueFactory(Symbol.width, Text2EmojiViewModel.MAX_WIDTH)
        width.valueFactory = spinnerValueFactory
    }

    private fun bindWidgets() {
        width.disableProperty().bind(widthEnabled.selectedProperty().not())
        separator.disableProperty().bind(separatorEnabled.selectedProperty().not())

        viewModel.text.bind(text.textProperty())
        viewModel.textEmoji.bind(textEmoji.textProperty())
        viewModel.emptyEmoji.bind(emptyEmoji.textProperty())
        viewModel.separatorEnabled.bind(separatorEnabled.selectedProperty())
        viewModel.separator.bind(separator.textProperty())
        viewModel.widthEnabled.bind(widthEnabled.selectedProperty())
        viewModel.width.bind(width.valueProperty())
        emojis.textProperty().bind(viewModel.emojis)
    }

}
