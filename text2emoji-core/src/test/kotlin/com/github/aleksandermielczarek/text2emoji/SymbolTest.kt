package com.github.aleksandermielczarek.text2emoji

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Created by Aleksander Mielczarek on 09.07.2017.
 */
class SymbolTest {

    @Test
    fun shouldHaveSameHeightForAll() {
        val heights = Symbol.values()
                .map { it.matrix }
                .map { it.height() }
                .distinct()
                .count()
        assertEquals(1, heights)
    }

    @Test
    fun shouldHaveSameWidthForAll() {
        val widths = Symbol.values()
                .map { it.matrix }
                .map { it.equalsRows() }
                .distinct()
                .count()
        assertEquals(1, widths)
    }

    @Test
    fun shouldHaveOnlyAllowedValues() {
        val allowed = Symbol.values()
                .map { it.matrix }
                .flatMap { it.columns }
                .map { allHaveAllowedValues(it) }
                .all { true }
        assertTrue(allowed)
    }

    private fun allHaveAllowedValues(values: List<String>): Boolean {
        return values.all { it == Symbol.PLACEHOLDER_EMPTY || it == Symbol.PLACEHOLDER_FULL }
    }
}