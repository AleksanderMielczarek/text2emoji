package com.github.aleksandermielczarek.text2emoji

import com.github.aleksandermielczarek.text2emoji.ui.CopyToClipboardUseCase
import com.github.aleksandermielczarek.text2emoji.ui.Router
import com.github.aleksandermielczarek.text2emoji.ui.Text2EmojiPresenter
import com.github.aleksandermielczarek.text2emoji.ui.Text2EmojiUseCase
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Aleksander Mielczarek on 16.07.2017.
 */
@RunWith(MockitoJUnitRunner.StrictStubs::class)
class Text2EmojiPresenterTest {

    @InjectMocks
    lateinit var presenter: Text2EmojiPresenter

    @Mock
    lateinit var text2emojiUseCase: Text2EmojiUseCase

    @Mock
    lateinit var copyToClipboardUseCase: CopyToClipboardUseCase

    @Mock
    lateinit var router: Router

    @Before
    fun setup() {
        presenter.text.set("")
        presenter.textEmoji.set("")
        presenter.emptyEmoji.set("")
        presenter.separatorEnabled.set(true)
        presenter.separator.set("")
        presenter.widthEnabled.set(true)
        presenter.width.set(5)
    }

    @Test
    fun shouldShowResultForAnyInput() {
        whenever(text2emojiUseCase.text2emoji(anyString(), anyString(), anyString(), anyBoolean(), anyString(), anyBoolean(), anyInt())).thenReturn("emoji")
        presenter.observeText2emoji()
        assertEquals("emoji", presenter.emojis.value)
    }
}