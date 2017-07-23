package com.github.aleksandermielczarek.text2emoji.ui

import com.github.aleksandermielczarek.text2emoji.resource.MessageFactory
import com.github.aleksandermielczarek.text2emoji.resource.ResourceFactory
import javafx.scene.control.Alert
import javafx.scene.web.WebView
import javafx.stage.Stage
import javax.inject.Inject

class Text2EmojiRouter @Inject constructor(val messageFactory: MessageFactory, val resourceFactory: ResourceFactory) : Router {

    override fun openAboutDialog() {
        val alert = createAboutDialog()
        alert.show()
    }

    private fun createAboutDialog(): Alert {
        val alert = Alert(Alert.AlertType.INFORMATION)

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