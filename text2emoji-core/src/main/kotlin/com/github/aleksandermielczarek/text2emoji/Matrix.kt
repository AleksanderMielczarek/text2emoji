package com.github.aleksandermielczarek.text2emoji

/**
 * Created by Aleksander Mielczarek on 09.07.2017.
 */
class Matrix<T> {

    val columns: MutableList<MutableList<T>> = mutableListOf()

    companion object {

        fun <T> identity(value: T, size: Int): Matrix<T> {
            return identity(value, size, size)
        }

        fun <T> identity(value: T, rows: Int, columns: Int): Matrix<T> {
            val matrix = Matrix<T>()
            for (i in 1..columns) {
                val row: MutableList<T> = mutableListOf()
                for (j in 1..rows) {
                    row.add(value)
                }
                matrix.columns.add(row)
            }
            return matrix
        }

        fun <T> of(vararg rows: List<T>): Matrix<T> {
            val matrix = Matrix<T>()
            rows.forEach { matrix.columns.add(it.toMutableList()) }
            return matrix
        }

    }

    fun add(other: Matrix<T>) {
        addNewRows(other.height())
        fillRowsAtTheBottom(other)
    }

    fun addAtTheBottom(other: Matrix<T>) {
        addNewRowsAtTheBottom(other.height())
        fillRowsAtTheBottom(other)
    }

    fun replace(old: T, new: T) {
        columns.forEach { replaceInRow(it, old, new) }
    }

    fun joinToString(separator: String): List<String> {
        return columns.map { it.joinToString(separator) }
    }

    fun height() = columns.size

    fun width() = columns
            .map { it.size }
            .max() ?: 0

    fun minWidth() = columns
            .map { it.size }
            .min() ?: 0

    fun equalsRows() = columns
            .map { it.size }
            .distinct()
            .count() == 1

    private fun addNewRows(newRows: Int) {
        if (newRows > height()) {
            addNewRowsAtTheBottom(newRows - height())
        }
    }

    private fun addNewRowsAtTheBottom(newRows: Int) {
        for (i in 1..newRows) {
            columns.add(mutableListOf())
        }
    }

    private fun fillRowsAtTheBottom(other: Matrix<T>) {
        val otherIterator = other.columns.asReversed().iterator()
        val reversedIterator = columns.asReversed().listIterator()
        while (otherIterator.hasNext()) {
            reversedIterator.next().addAll(otherIterator.next())
        }
    }

    private fun replaceInRow(row: MutableList<T>, old: T, new: T) {
        row.replaceAll {
            if (it == old) {
                new
            } else {
                it
            }
        }
    }
}