package com.github.aleksandermielczarek.text2emoji

import com.google.inject.Guice
import com.google.inject.Injector
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Stage
import java.util.*

/**
 * Created by Aleksander Mielczarek on 09.07.2017.
 */
class Text2EmojiApplication : Application() {

    companion object : Injector by Guice.createInjector()

    override fun start(primaryStage: Stage) {
        val bundle = ResourceBundle.getBundle("bundle/bundle", Locale(""))
        val root = createRoot(bundle)
        val scene = Scene(root)
        setupStage(primaryStage, bundle, scene)
        primaryStage.show()
    }

    private fun createRoot(bundle: ResourceBundle): Parent {
        val fxml = javaClass.getResource("/fxml/Text2Emoji.fxml")
        val root: Parent = FXMLLoader.load(fxml, bundle)
        return root
    }

    private fun setupStage(primaryStage: Stage, bundle: ResourceBundle, scene: Scene) {
        primaryStage.title = bundle.getString("app.name")
        primaryStage.icons.add(Image("image/icon.png"))
        primaryStage.scene = scene
    }
}

fun main(args: Array<String>) {
    Application.launch(Text2EmojiApplication::class.java, *args)
}