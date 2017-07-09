package com.github.aleksandermielczarek.text2emoji

/**
 * Created by Aleksander Mielczarek on 08.07.2017.
 */
enum class Symbol(val symbol: Char?, val matrix: Matrix<String>) {
    //TODO add symbols
    A('A', Matrix.of(
            listOf(" ", "X", " "),
            listOf("X", " ", "X"),
            listOf("X", "X", "X"),
            listOf("X", " ", "X"),
            listOf("X", " ", "X"))),

    B('B', Matrix.of(
            listOf("X", "X", " "),
            listOf("X", " ", "X"),
            listOf("X", "X", " "),
            listOf("X", " ", "X"),
            listOf("X", "X", " "))),

    C('C', Matrix.of(
            listOf(" ", "X", "X"),
            listOf("X", " ", " "),
            listOf("X", " ", " "),
            listOf("X", " ", " "),
            listOf(" ", "X", "X"))),

    D('D', Matrix.of(
            listOf("X", "X", " "),
            listOf("X", " ", "X"),
            listOf("X", " ", "X"),
            listOf("X", " ", "X"),
            listOf("X", "X", " "))),

    E('E', Matrix.of(
            listOf("X", "X", "X"),
            listOf("X", " ", " "),
            listOf("X", "X", " "),
            listOf("X", " ", " "),
            listOf("X", "X", "X"))),

    F('F', Matrix.of(
            listOf("X", "X", "X"),
            listOf("X", " ", " "),
            listOf("X", "X", " "),
            listOf("X", " ", " "),
            listOf("X", " ", " "))),

    G('G', Matrix.of(
            listOf("X", "X", "X"),
            listOf("X", " ", " "),
            listOf("X", " ", " "),
            listOf("X", " ", "X"),
            listOf("X", "X", "X"))),

    H('H', Matrix.of(
            listOf("X", " ", "X"),
            listOf("X", " ", "X"),
            listOf("X", "X", "X"),
            listOf("X", " ", "X"),
            listOf("X", " ", "X"))),

    I('I', Matrix.of(
            listOf("X", "X", "X"),
            listOf(" ", "X", " "),
            listOf(" ", "X", " "),
            listOf(" ", "X", " "),
            listOf("X", "X", "X"))),

    J('J', Matrix.of(
            listOf(" ", " ", "X"),
            listOf(" ", " ", "X"),
            listOf(" ", " ", "X"),
            listOf("X", " ", "X"),
            listOf(" ", "X", " "))),

    K('K', Matrix.of(
            listOf("X", " ", "X"),
            listOf("X", " ", "X"),
            listOf("X", "X", " "),
            listOf("X", " ", "X"),
            listOf("X", " ", "X"))),

    L('L', Matrix.of(
            listOf("X", " ", " "),
            listOf("X", " ", " "),
            listOf("X", " ", " "),
            listOf("X", " ", " "),
            listOf("X", "X", "X"))),

    M('M', Matrix.of(
            listOf("X", " ", " ", " ", "X"),
            listOf("X", "X", " ", "X", "X"),
            listOf("X", " ", "X", " ", "X"),
            listOf("X", " ", " ", " ", "X"),
            listOf("X", " ", " ", " ", "X"))),

    N('N', Matrix.of(
            listOf("X", " ", " ", " ", "X"),
            listOf("X", "X", " ", " ", "X"),
            listOf("X", " ", "X", " ", "X"),
            listOf("X", " ", " ", "X", "X"),
            listOf("X", " ", " ", " ", "X"))),

    O('O', Matrix.of(
            listOf("X", "X", "X"),
            listOf("X", " ", "X"),
            listOf("X", " ", "X"),
            listOf("X", " ", "X"),
            listOf("X", "X", "X"))),

    P('P', Matrix.of(
            listOf("X", "X", "X"),
            listOf("X", " ", "X"),
            listOf("X", "X", "X"),
            listOf("X", " ", " "),
            listOf("X", " ", " "))),

    Q('Q', Matrix.of(
            listOf("X", "X", "X", "X"),
            listOf("X", " ", " ", "X"),
            listOf("X", " ", " ", "X"),
            listOf("X", " ", "X", "X"),
            listOf("X", "X", "X", "X"))),

    R('R', Matrix.of(
            listOf("X", "X", "X"),
            listOf("X", " ", "X"),
            listOf("X", "X", " "),
            listOf("X", " ", "X"),
            listOf("X", " ", "X"))),

    S('S', Matrix.of(
            listOf(" ", "X", "X"),
            listOf("X", " ", " "),
            listOf(" ", "X", " "),
            listOf(" ", " ", "X"),
            listOf("X", "X", " "))),

    T('T', Matrix.of(
            listOf("X", "X", "X"),
            listOf(" ", "X", " "),
            listOf(" ", "X", " "),
            listOf(" ", "X", " "),
            listOf(" ", "X", " "))),

    U('U', Matrix.of(
            listOf("X", " ", "X"),
            listOf("X", " ", "X"),
            listOf("X", " ", "X"),
            listOf("X", " ", "X"),
            listOf("X", "X", "X"))),

    V('V', Matrix.of(
            listOf("X", " ", "X"),
            listOf("X", " ", "X"),
            listOf("X", " ", "X"),
            listOf("X", " ", "X"),
            listOf(" ", "X", " "))),

    W('W', Matrix.of(
            listOf("X", " ", " ", " ", "X"),
            listOf("X", " ", " ", " ", "X"),
            listOf("X", " ", " ", " ", "X"),
            listOf("X", " ", "X", " ", "X"),
            listOf(" ", "X", " ", "X", " "))),

    X('X', Matrix.of(
            listOf("X", " ", "X"),
            listOf("X", " ", "X"),
            listOf(" ", "X", " "),
            listOf("X", " ", "X"),
            listOf("X", " ", "X"))),

    Y('Y', Matrix.of(
            listOf("X", " ", "X"),
            listOf("X", " ", "X"),
            listOf(" ", "X", " "),
            listOf(" ", "X", " "),
            listOf(" ", "X", " "))),

    Z('Z', Matrix.of(
            listOf("X", "X", "X"),
            listOf(" ", " ", "X"),
            listOf(" ", "X", " "),
            listOf("X", " ", " "),
            listOf("X", "X", "X"))),

    /*A("A", Matrix.of(
            listOf(" ", " ", " "),
            listOf(" ", " ", " "),
            listOf(" ", " ", " "),
            listOf(" ", " ", " "),
            listOf(" ", " ", " "))),*/

    SPACE(' ', Matrix.identity(" ", 2, 5)),

    SEPARATOR(Matrix.identity(" ", 1, 5)),

    DEFAULT(Matrix.identity("X", 3, 5));

    constructor(matrix: Matrix<String>) : this(null, matrix)

    companion object {

        const val PLACEHOLDER_EMPTY = " "
        const val PLACEHOLDER_FULL = "X"

        val height = values()
                .map { it.matrix }
                .map { it.height() }
                .first()

        val width = values()
                .map { it.matrix }
                .map { it.width() }
                .max() ?: 0

        val symbols: Map<Char, Symbol> = Symbol.values()
                .filter { it.symbol != null }
                .associateBy { it.symbol!! }

        fun fromCharacter(character: Char): Symbol {
            return symbols.getOrDefault(character.toUpperCase(), DEFAULT)
        }
    }

}
