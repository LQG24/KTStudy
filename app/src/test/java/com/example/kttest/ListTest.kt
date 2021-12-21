package com.example.kttest

import org.junit.Test

/**
 * List 相关操作
 */
class ListTest {
    @Test
    fun test() {
        val numbers = listOf(1, 2, 3, 4)
        println(numbers[0])
        //numbers.get(5)                         // exception!
        println(numbers.getOrNull(5))             // null
        //用于计算默认值的函数，如果集合中不存在索引，则返回默认值。
        println(numbers.getOrElse(5) { it })

        val numbers1 = (0..13).toList()
        println(numbers1.subList(3,6))

        val mutableNumber = mutableListOf(1,2,3,4)
        mutableNumber.fill(3)
        println("fill:$mutableNumber")
    }
}