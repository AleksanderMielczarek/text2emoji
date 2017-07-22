package com.github.aleksandermielczarek.text2emoji.resource

import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Aleksander Mielczarek on 22.07.2017.
 */
@Singleton
class MessageFactory @Inject constructor(resources: ResourceBundle) {

    val appName = resources.getString("app.name")!!
    val labelText = resources.getString("label.text")!!
    val labelEmoji = resources.getString("label.emoji")!!
    val labelEmpty = resources.getString("label.empty")!!
    val labelSeparator = resources.getString("label.separator")!!
    val labelWidth = resources.getString("label.width")!!
    val labelCopy = resources.getString("label.copy")!!
    val menuLabelHelp = resources.getString("menu.label.help")!!
    val menuLabelAbout = resources.getString("menu.label.about")!!
    val dialogAboutLicense = resources.getString("dialog.about.license")!!

}