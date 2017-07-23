package com.github.aleksandermielczarek.text2emoji.ui

import com.github.thomasnield.rxkotlinfx.toBinding
import com.github.thomasnield.rxkotlinfx.toObservable
import io.reactivex.rxkotlin.Observables
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import javax.inject.Inject

/**
 * Created by Aleksander Mielczarek on 16.07.2017.
 */
class Text2EmojiPresenter @Inject constructor(val text2EmojiUseCase: Text2EmojiUseCase, val copyToClipboardUseCase: CopyToClipboardUseCase, val router: Router) : Presenter {

    companion object {

        val MAX_WIDTH = 100
    }

    override val text = SimpleStringProperty()
    override val textEmoji = SimpleStringProperty()
    override val emptyEmoji = SimpleStringProperty()
    override val separatorEnabled = SimpleBooleanProperty()
    override val separator = SimpleStringProperty()
    override val widthEnabled = SimpleBooleanProperty()
    override val width = SimpleIntegerProperty()
    override val emojis = SimpleStringProperty()

    override fun copyToClipboard() {
        copyToClipboardUseCase.copyToClipboard(emojis.value)
    }

    override fun observeText2emoji() {
        emojis.bind(Observables.combineLatest(text.toObservable(),
                textEmoji.toObservable(),
                emptyEmoji.toObservable(),
                separatorEnabled.toObservable(),
                separator.toObservable(),
                widthEnabled.toObservable(),
                width.toObservable().map { it.toInt() },
                text2EmojiUseCase::text2emoji)
                .toBinding())
    }

    override fun openAboutDialog() {
        router.openAboutDialog()
    }

}