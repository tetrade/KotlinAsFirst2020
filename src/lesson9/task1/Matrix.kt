@file:Suppress("UNUSED_PARAMETER", "unused")

package lesson9.task1

import java.lang.IllegalArgumentException
import java.lang.IndexOutOfBoundsException

// Урок 9: проектирование классов
// Максимальное количество баллов = 40 (без очень трудных задач = 15)

/**
 * Ячейка матрицы: row = ряд, column = колонка
 */
data class Cell(val row: Int, val column: Int)


/**
 * Интерфейс, описывающий возможности матрицы. E = тип элемента матрицы
 */
interface Matrix<E> {
    /** Высота */
    val height: Int

    /** Ширина */
    val width: Int

    /**
     * Доступ к ячейке.
     * Методы могут бросить исключение, если ячейка не существует или пуста
     */
    operator fun get(row: Int, column: Int): E

    operator fun get(cell: Cell): E

    /**
     * Запись в ячейку.
     * Методы могут бросить исключение, если ячейка не существует
     */
    operator fun set(row: Int, column: Int, value: E)

    operator fun set(cell: Cell, value: E)
}

/**
 * Простая (2 балла)
 *
 * Метод для создания матрицы, должен вернуть РЕАЛИЗАЦИЮ Matrix<E>.
 * height = высота, width = ширина, e = чем заполнить элементы.
 * Бросить исключение IllegalArgumentException, если height или width <= 0.
 */
fun <E> createMatrix(height: Int, width: Int, e: E): Matrix<E> {
    if (height > 0 && width > 0) return MatrixImpl(height, width, e) else throw IllegalArgumentException()
}

/**
 * Средняя сложность (считается двумя задачами в 3 балла каждая)
 *
 * Реализация интерфейса "матрица"
 */

class MatrixImpl<E>(override val height: Int, override val width: Int, val e: E) : Matrix<E> {
    private val m: MutableList<MutableList<E>>

    init {
        val mu = mutableListOf<MutableList<E>>()
        for (i in 1..height) {
            val mW = mutableListOf<E>()
            for (j in 1..width) {
                mW.add(e)
            }
            mu.add(mW)
        }
        m = mu
    }

    override fun get(row: Int, column: Int): E = m[row][column]

    override fun get(cell: Cell): E = get(cell.row, cell.column)

    override fun set(row: Int, column: Int, value: E) {
        if (row !in 0 until height || column !in 0 until width) throw IndexOutOfBoundsException()
        m[row][column] = value
    }

    override fun set(cell: Cell, value: E) {
        set(cell.row, cell.column, value)
    }

    override fun equals(other: Any?) =
        other is MatrixImpl<*> && other.m == m


    override fun toString(): String {
        val out = mutableListOf<String>()
        for (row in 1..height) { out.add(m[row - 1].joinToString(separator = " ")) }
        return out.joinToString(separator = "\n")
    }
}



