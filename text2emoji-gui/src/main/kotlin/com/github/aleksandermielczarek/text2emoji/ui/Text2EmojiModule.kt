package com.github.aleksandermielczarek.text2emoji.ui

import com.google.inject.AbstractModule

/**
 * Created by Aleksander Mielczarek on 22.07.2017.
 */
class Text2EmojiModule : AbstractModule() {

    override fun configure() {
        bind(Text2EmojiUseCase::class.java).to(Text2EmojiUseCaseImpl::class.java)
        bind(CopyToClipboardUseCase::class.java).to(CopyToClipboardUseCaseImpl::class.java)
        bind(Presenter::class.java).to(Text2EmojiPresenter::class.java)
        bind(Router::class.java).to(Text2EmojiRouter::class.java)
    }
}