package com.github.aleksandermielczarek.text2emoji.ui.main

import com.github.aleksandermielczarek.text2emoji.Symbol
import com.github.aleksandermielczarek.text2emoji.Text2EmojiApplication
import com.github.aleksandermielczarek.text2emoji.resource.MessageFactory
import com.github.aleksandermielczarek.text2emoji.resource.ResourceFactory
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.control.Alert.AlertType
import javafx.scene.web.WebView
import javafx.stage.Stage
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

    @Inject
    private lateinit var messageFactory: MessageFactory

    @Inject
    private lateinit var resourceFactory: ResourceFactory

    @FXML
    fun initialize() {
        Text2EmojiApplication.injectMembers(this)
        initSpinner()
        bindViewModel()
        viewModel.observeText2emoji()
    }

    @FXML
    fun openAboutDialog() {
        val alert = createAboutDialog()
        alert.show()
    }

    @FXML
    fun copyToClipboard() {
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

    private fun createAboutDialog(): Alert {
        val alert = Alert(AlertType.INFORMATION)

        val alertStage = alert.dialogPane.scene.window as Stage
        alertStage.icons.add(resourceFactory.appIcon)

        alert.title = messageFactory.menuLabelAbout
        alert.headerText = messageFactory.appName

        val webView = WebView()
        webView.engine.loadContent(messageFactory.dialogAboutLicense)
        webView.setPrefSize(alert.width, 100.0)
        alert.dialogPane.content = webView

        return alert
    }

}