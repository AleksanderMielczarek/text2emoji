package com.github.aleksandermielczarek.text2emoji

import dagger.Component

/**
 * Created by Aleksander Mielczarek on 08.07.2017.
 */
@Component
interface Text2EmojiApplicationComponent {

    fun getApp(): Text2EmojiApplication

}
