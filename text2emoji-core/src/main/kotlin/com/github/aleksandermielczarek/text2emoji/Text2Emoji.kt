package com.github.aleksandermielczarek.text2emoji

import javax.inject.Inject


/**
 * Created by Aleksander Mielczarek on 08.07.2017.
 */
class Text2Emoji @Inject constructor() {

    companion object {

        const val WIDTH_NO_LIMIT = 0

    }

    fun text2emoji(text: String, textEmoji: String, emptyEmoji: String, emojiSeparator: String, maxWidthLimit: Int = WIDTH_NO_LIMIT): List<String> {
        val widthLimit = checkWidthLimit(maxWidthLimit)
        val hasWidthLimit = widthLimit(widthLimit)
        val matrix: Matrix<String> = text2matrix(text, hasWidthLimit, widthLimit)
        matrix.replace(Symbol.PLACEHOLDER_FULL, textEmoji)
        matrix.replace(Symbol.PLACEHOLDER_EMPTY, emptyEmoji)
        return matrix.joinToString(emojiSeparator)
    }

    private fun checkWidthLimit(widthLimit: Int): Int {
        if (!widthLimit(widthLimit)) {
            return widthLimit
        }
        if (widthLimit < Symbol.width) {
            return Symbol.width
        }
        return widthLimit
    }

    private fun widthLimit(widthLimit: Int): Boolean {
        return widthLimit > WIDTH_NO_LIMIT
    }

    private fun text2matrix(text: String, hasWidthLimit: Boolean, widthLimit: Int): Matrix<String> {
        if (hasWidthLimit) {
            return text2matrixWithWidthLimit(text, widthLimit)
        } else {
            return text2matrixWithNoWidthLimit(text)
        }
    }

    private fun text2matrixWithNoWidthLimit(text: String): Matrix<String> {
        val symbols = createSymbols(text)
        addSymbolSeparators(symbols)
        return createMatrixWithNoWidthLimit(symbols)
    }

    private fun createMatrixWithNoWidthLimit(symbols: List<Symbol>): Matrix<String> {
        val matrix: Matrix<String> = Matrix()
        symbols.map { it.matrix }
                .forEach { matrix.add(it) }
        return matrix
    }

    private fun createSymbols(text: String): MutableList<Symbol> {
        return text.map { Symbol.fromCharacter(it) }
                .toMutableList()
    }

    private fun addSymbolSeparators(symbols: MutableList<Symbol>) {
        val symbolsIterator = symbols.listIterator()

        while (symbolsIterator.hasNext()) {
            addSymbolSeparator(symbolsIterator)
        }
    }

    private fun addSymbolSeparator(symbolsIterator: MutableListIterator<Symbol>) {
        val current = symbolsIterator.next()

        if (symbolsIterator.hasNext()) {
            val next = symbolsIterator.next()
            symbolsIterator.previous()

            if (shouldAddSeparator(current, next)) {
                symbolsIterator.add(Symbol.SEPARATOR)
            }
        }
    }

    private fun shouldAddSeparator(current: Symbol, next: Symbol) = current != Symbol.SPACE && next != Symbol.SPACE

    private fun text2matrixWithWidthLimit(text: String, widthLimit: Int): Matrix<String> {
        val symbols = createSymbols(text)
        return createMatrixWithWidthLimit(symbols, widthLimit)
    }

    private fun createMatrixWithWidthLimit(symbols: List<Symbol>, widthLimit: Int): Matrix<String> {
        val matrix: Matrix<String> = Matrix()
        val symbolsIterator = symbols.listIterator()

        while (symbolsIterator.hasNext()) {
            val current = symbolsIterator.next()

            if (!canAddSymbol(current, matrix, widthLimit)) {
                addSymbolInNewRow(matrix, widthLimit, current, symbolsIterator)
            } else {
                addSymbolInCurrentRow(matrix, current, symbolsIterator, widthLimit)
            }
        }

        fillRow(matrix, widthLimit)
        return matrix
    }

    private fun addSymbolInCurrentRow(matrix: Matrix<String>, current: Symbol, symbolsIterator: ListIterator<Symbol>, widthLimit: Int) {
        matrix.add(current.matrix)
        addSymbolSeparator(current, matrix, symbolsIterator, widthLimit)
    }

    private fun addSymbolInNewRow(matrix: Matrix<String>, widthLimit: Int, current: Symbol, symbolsIterator: ListIterator<Symbol>) {
        if (current != Symbol.SPACE) {
            fillRow(matrix, widthLimit)
            matrix.addAtTheBottom(Matrix.identity(Symbol.PLACEHOLDER_EMPTY, widthLimit, 1))
            matrix.addAtTheBottom(current.matrix)
            addSymbolSeparator(current, matrix, symbolsIterator, widthLimit)
        }
    }

    private fun fillRow(matrix: Matrix<String>, maxWidthLimit: Int) {
        matrix.add(Matrix.identity(Symbol.PLACEHOLDER_EMPTY, maxWidthLimit - matrix.minWidth(), Symbol.height))
    }

    private fun canAddSymbol(symbol: Symbol, matrix: Matrix<String>, maxWidthLimit: Int) = matrix.minWidth() + symbol.matrix.width() <= maxWidthLimit

    private fun addSymbolSeparator(current: Symbol, matrix: Matrix<String>, symbolsIterator: ListIterator<Symbol>, maxWidthLimit: Int) {
        if (symbolsIterator.hasNext()) {
            val next = symbolsIterator.next()
            symbolsIterator.previous()

            if (shouldAddSeparator(current, next) && canAddSymbol(Symbol.SEPARATOR, matrix, maxWidthLimit)) {
                matrix.add(Symbol.SEPARATOR.matrix)
            }
        }
    }

}
