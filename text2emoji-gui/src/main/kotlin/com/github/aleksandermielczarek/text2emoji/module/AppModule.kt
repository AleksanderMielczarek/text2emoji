package com.github.aleksandermielczarek.text2emoji.module

import com.google.inject.AbstractModule
import java.util.*

/**
 * Created by Aleksander Mielczarek on 22.07.2017.
 */
class AppModule : AbstractModule() {

    override fun configure() {
        bind(ResourceBundle::class.java).toInstance(ResourceBundle.getBundle("bundle/bundle", Locale("")))
    }

}