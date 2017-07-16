package com.github.aleksandermielczarek.text2emoji

import com.nhaarman.mockito_kotlin.whenever
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Aleksander Mielczarek on 16.07.2017.
 */
@RunWith(MockitoJUnitRunner.StrictStubs::class)
class Text2EmojiViewModelTest {

    @InjectMocks
    lateinit var viewModel: Text2EmojiViewModel

    @Mock
    lateinit var text2emoji: Text2Emoji

    @Before
    fun setup() {
        viewModel.text.set("")
        viewModel.textEmoji.set("")
        viewModel.emptyEmoji.set("")
        viewModel.separatorEnabled.set(true)
        viewModel.separator.set("")
        viewModel.widthEnabled.set(true)
        viewModel.width.set(5)
    }

    @Test
    fun shouldShowResultForAnyInput() {
        whenever(text2emoji.text2emoji(anyString(), anyString(), anyString(), anyString(), anyInt())).thenReturn("emoji")
        viewModel.observeText2emoji()
        assertEquals("emoji", viewModel.emojis.value)
    }
}