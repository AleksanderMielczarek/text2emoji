package com.github.aleksandermielczarek.text2emoji

import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Aleksander Mielczarek on 09.07.2017.
 */
@RunWith(MockitoJUnitRunner.StrictStubs::class)
class MatrixTest {

    lateinit var matrix: Matrix<String>

    @Before
    fun setUp() {
        matrix = Matrix.identity("X", 3)
    }

    @Test
    fun shouldReplaceXToY() {
        matrix.replace("X", "Y")
        val replaced = matrix.columns
                .flatMap { it }
                .all { it == "Y" }
        assertTrue(replaced)
    }

}