package com.github.aleksandermielczarek.text2emoji

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Aleksander Mielczarek on 08.07.2017.
 */
@RunWith(MockitoJUnitRunner.StrictStubs::class)
class Text2EmojiTest {

    companion object {

        const val TEXT_EMOJI = ":face:"
        const val EMPTY_EMOJI = ":empty:"
        const val EMOJI_SEPARATOR = " "

        val EMOJI_A = ":empty: :face: :empty:" + System.lineSeparator() +
                ":face: :empty: :face:" + System.lineSeparator() +
                ":face: :face: :face:" + System.lineSeparator() +
                ":face: :empty: :face:" + System.lineSeparator() +
                ":face: :empty: :face:"

        val EMOJI_A_B =
                ":empty: :face: :empty: :empty: :face: :face: :empty:" + System.lineSeparator() +
                        ":face: :empty: :face: :empty: :face: :empty: :face:" + System.lineSeparator() +
                        ":face: :face: :face: :empty: :face: :face: :empty:" + System.lineSeparator() +
                        ":face: :empty: :face: :empty: :face: :empty: :face:" + System.lineSeparator() +
                        ":face: :empty: :face: :empty: :face: :face: :empty:"

        val EMOJI_A_B_C =
                ":empty: :face: :empty: :empty: :face: :face: :empty: :empty: :empty: :face: :face:" + System.lineSeparator() +
                        ":face: :empty: :face: :empty: :face: :empty: :face: :empty: :face: :empty: :empty:" + System.lineSeparator() +
                        ":face: :face: :face: :empty: :face: :face: :empty: :empty: :face: :empty: :empty:" + System.lineSeparator() +
                        ":face: :empty: :face: :empty: :face: :empty: :face: :empty: :face: :empty: :empty:" + System.lineSeparator() +
                        ":face: :empty: :face: :empty: :face: :face: :empty: :empty: :empty: :face: :face:"

        val EMOJI_A_SPACE_B =
                ":empty: :face: :empty: :empty: :empty: :face: :face: :empty:" + System.lineSeparator() +
                        ":face: :empty: :face: :empty: :empty: :face: :empty: :face:" + System.lineSeparator() +
                        ":face: :face: :face: :empty: :empty: :face: :face: :empty:" + System.lineSeparator() +
                        ":face: :empty: :face: :empty: :empty: :face: :empty: :face:" + System.lineSeparator() +
                        ":face: :empty: :face: :empty: :empty: :face: :face: :empty:"

        val EMOJI_UNKNOWN =
                ":face: :face: :face:" + System.lineSeparator() +
                        ":face: :face: :face:" + System.lineSeparator() +
                        ":face: :face: :face:" + System.lineSeparator() +
                        ":face: :face: :face:" + System.lineSeparator() +
                        ":face: :face: :face:"

        val EMOJI_A_NEW_LINE_B_WIDTH_FIVE =
                ":empty: :face: :empty: :empty: :empty:" + System.lineSeparator() +
                        ":face: :empty: :face: :empty: :empty:" + System.lineSeparator() +
                        ":face: :face: :face: :empty: :empty:" + System.lineSeparator() +
                        ":face: :empty: :face: :empty: :empty:" + System.lineSeparator() +
                        ":face: :empty: :face: :empty: :empty:" + System.lineSeparator() +
                        ":empty: :empty: :empty: :empty: :empty:" + System.lineSeparator() +
                        ":face: :face: :empty: :empty: :empty:" + System.lineSeparator() +
                        ":face: :empty: :face: :empty: :empty:" + System.lineSeparator() +
                        ":face: :face: :empty: :empty: :empty:" + System.lineSeparator() +
                        ":face: :empty: :face: :empty: :empty:" + System.lineSeparator() +
                        ":face: :face: :empty: :empty: :empty:"

        val EMOJI_A_B_NEW_LINE_C_WIDTH_SEVEN =
                ":empty: :face: :empty: :empty: :face: :face: :empty:" + System.lineSeparator() +
                        ":face: :empty: :face: :empty: :face: :empty: :face:" + System.lineSeparator() +
                        ":face: :face: :face: :empty: :face: :face: :empty:" + System.lineSeparator() +
                        ":face: :empty: :face: :empty: :face: :empty: :face:" + System.lineSeparator() +
                        ":face: :empty: :face: :empty: :face: :face: :empty:" + System.lineSeparator() +
                        ":empty: :empty: :empty: :empty: :empty: :empty: :empty:" + System.lineSeparator() +
                        ":empty: :face: :face: :empty: :empty: :empty: :empty:" + System.lineSeparator() +
                        ":face: :empty: :empty: :empty: :empty: :empty: :empty:" + System.lineSeparator() +
                        ":face: :empty: :empty: :empty: :empty: :empty: :empty:" + System.lineSeparator() +
                        ":face: :empty: :empty: :empty: :empty: :empty: :empty:" + System.lineSeparator() +
                        ":empty: :face: :face: :empty: :empty: :empty: :empty:"

    }

    @InjectMocks
    private lateinit var text2Emoji: Text2Emoji

    @Test
    fun shouldReturnAForLowerA() {
        val text = text2Emoji.text2emoji("a", TEXT_EMOJI, EMPTY_EMOJI, EMOJI_SEPARATOR)
        assertEquals(EMOJI_A, text)
    }

    @Test
    fun shouldReturnAForGreaterA() {
        val text = text2Emoji.text2emoji("A", TEXT_EMOJI, EMPTY_EMOJI, EMOJI_SEPARATOR)
        assertEquals(EMOJI_A, text)
    }

    @Test
    fun shouldReturnASeparatorBForAB() {
        val text = text2Emoji.text2emoji("AB", TEXT_EMOJI, EMPTY_EMOJI, EMOJI_SEPARATOR)
        assertEquals(EMOJI_A_B, text)
    }

    @Test
    fun shouldReturnASeparatorBSeparatorCForABC() {
        val text = text2Emoji.text2emoji("ABC", TEXT_EMOJI, EMPTY_EMOJI, EMOJI_SEPARATOR)
        assertEquals(EMOJI_A_B_C, text)
    }

    @Test
    fun shouldReturnASpaceBForASpaceB() {
        val text = text2Emoji.text2emoji("A B", TEXT_EMOJI, EMPTY_EMOJI, EMOJI_SEPARATOR)
        assertEquals(EMOJI_A_SPACE_B, text)
    }

    @Test
    fun shouldReturnDefaultForUnknown() {
        val text = text2Emoji.text2emoji("#", TEXT_EMOJI, EMPTY_EMOJI, EMOJI_SEPARATOR)
        assertEquals(EMOJI_UNKNOWN, text)
    }

    @Test
    fun shouldReturnANewLineBForASpaceBWidthFive() {
        val text = text2Emoji.text2emoji("A B", TEXT_EMOJI, EMPTY_EMOJI, EMOJI_SEPARATOR, 5)
        assertEquals(EMOJI_A_NEW_LINE_B_WIDTH_FIVE, text)
    }

    @Test
    fun shouldReturnANewLineBForABWidthFive() {
        val text = text2Emoji.text2emoji("AB", TEXT_EMOJI, EMPTY_EMOJI, EMOJI_SEPARATOR, 5)
        assertEquals(EMOJI_A_NEW_LINE_B_WIDTH_FIVE, text)
    }

    @Test
    fun shouldReturnASeparatorBNewLineCForABSpaceCWidthSeven() {
        val text = text2Emoji.text2emoji("AB C", TEXT_EMOJI, EMPTY_EMOJI, EMOJI_SEPARATOR, 7)
        assertEquals(EMOJI_A_B_NEW_LINE_C_WIDTH_SEVEN, text)
    }

    @Test
    fun shouldReturnANewLineBForASpaceBWidthThree() {
        val text = text2Emoji.text2emoji("A B", TEXT_EMOJI, EMPTY_EMOJI, EMOJI_SEPARATOR, 3)
        assertEquals(EMOJI_A_NEW_LINE_B_WIDTH_FIVE, text)
    }

    @Test
    fun shouldReturnASeparatorBForABWidth0() {
        val text = text2Emoji.text2emoji("AB", TEXT_EMOJI, EMPTY_EMOJI, EMOJI_SEPARATOR, 0)
        assertEquals(EMOJI_A_B, text)
    }

}
