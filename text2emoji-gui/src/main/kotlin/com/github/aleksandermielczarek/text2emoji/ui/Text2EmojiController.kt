package com.github.aleksandermielczarek.text2emoji.ui

import com.github.aleksandermielczarek.text2emoji.Symbol
import com.github.aleksandermielczarek.text2emoji.Text2EmojiApplication
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
    private lateinit var presenter: Presenter

    @FXML
    fun initialize() {
        Text2EmojiApplication.createChildInjector(Text2EmojiModule()).injectMembers(this)
        initSpinner()
        bindPresenter()
        presenter.observeText2emoji()
    }

    @FXML
    fun openAboutDialog() {
        presenter.openAboutDialog()
    }

    @FXML
    fun copyToClipboard() {
        presenter.copyToClipboard()
    }

    private fun initSpinner() {
        val spinnerValueFactory = SpinnerValueFactory.IntegerSpinnerValueFactory(Symbol.width, Text2EmojiPresenter.MAX_WIDTH)
        width.valueFactory = spinnerValueFactory
    }

    private fun bindPresenter() {
        presenter.text.bind(text.textProperty())
        presenter.textEmoji.bind(textEmoji.textProperty())
        presenter.emptyEmoji.bind(emptyEmoji.textProperty())
        presenter.separatorEnabled.bind(separatorEnabled.selectedProperty())
        presenter.separator.bind(separator.textProperty())
        presenter.widthEnabled.bind(widthEnabled.selectedProperty())
        presenter.width.bind(width.valueProperty())

        width.disableProperty().bind(presenter.widthEnabled.not())
        separator.disableProperty().bind(presenter.separatorEnabled.not())
        emojis.textProperty().bind(presenter.emojis)
    }

}
