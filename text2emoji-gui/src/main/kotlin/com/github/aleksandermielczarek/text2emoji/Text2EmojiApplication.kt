package com.github.aleksandermielczarek.text2emoji

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import java.util.*

/**
 * Created by Aleksander Mielczarek on 09.07.2017.
 */
class Text2EmojiApplication : Application() {

    override fun start(primaryStage: Stage) {
        val fxml = javaClass.getResource("/fxml/Text2Emoji.fxml")
        val bundle = ResourceBundle.getBundle("bundle/bundle", Locale(""))
        val root: Parent = FXMLLoader.load(fxml, bundle)

        val scene = Scene(root)

        primaryStage.title = bundle.getString("app.name")
        primaryStage.scene = scene
        primaryStage.show()
    }
}

fun main(args: Array<String>) {
    Application.launch(Text2EmojiApplication::class.java, *args)
}