package com.github.aleksandermielczarek.text2emoji

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.*
import javax.inject.Inject

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

    @Inject
    private lateinit var viewModel: Text2EmojiViewModel

    @FXML
    fun initialize() {
        Text2EmojiApplication.injectMembers(this)
        initSpinner()
        bindViewModel()
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

    private fun bindViewModel() {
        viewModel.text.bind(text.textProperty())
        viewModel.textEmoji.bind(textEmoji.textProperty())
        viewModel.emptyEmoji.bind(emptyEmoji.textProperty())
        viewModel.separatorEnabled.bind(separatorEnabled.selectedProperty())
        viewModel.separator.bind(separator.textProperty())
        viewModel.widthEnabled.bind(widthEnabled.selectedProperty())
        viewModel.width.bind(width.valueProperty())

        width.disableProperty().bind(viewModel.widthEnabled.not())
        separator.disableProperty().bind(viewModel.separatorEnabled.not())
        emojis.textProperty().bind(viewModel.emojis)
    }

}
