package com.github.aleksandermielczarek.text2emoji

import com.github.aleksandermielczarek.text2emoji.module.AppModule
import com.github.aleksandermielczarek.text2emoji.resource.MessageFactory
import com.github.aleksandermielczarek.text2emoji.resource.ResourceFactory
import com.google.inject.Guice
import com.google.inject.Injector
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import java.util.*
import javax.inject.Inject

/**
 * Created by Aleksander Mielczarek on 09.07.2017.
 */
class Text2EmojiApplication : Application() {

    @Inject
    private lateinit var resources: ResourceBundle

    @Inject
    private lateinit var messageFactory: MessageFactory

    @Inject
    private lateinit var resourceFactory: ResourceFactory

    companion object : Injector by Guice.createInjector(AppModule())

    override fun start(primaryStage: Stage) {
        injectMembers(this)
        val root = createRoot(resources)
        val scene = Scene(root)
        setupStage(primaryStage, resources, scene)
        primaryStage.show()
    }

    private fun createRoot(bundle: ResourceBundle): Parent {
        val fxml = javaClass.getResource("/fxml/Text2Emoji.fxml")
        val root: Parent = FXMLLoader.load(fxml, bundle)
        return root
    }

    private fun setupStage(primaryStage: Stage, bundle: ResourceBundle, scene: Scene) {
        primaryStage.title = messageFactory.appName
        primaryStage.icons.add(resourceFactory.appIcon)
        primaryStage.scene = scene
    }
}

fun main(args: Array<String>) {
    Application.launch(Text2EmojiApplication::class.java, *args)
}