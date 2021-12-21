package com.example.kttest

import org.junit.Test

/**
 *Set相关操作
 */
class SetTest {
    @Test
    fun test(){
        val numbers = setOf("one", "two", "three")

        println(numbers union setOf("four", "five"))
        //要将两个集合合并为一个（并集），可使用 union() 函数。
        println(setOf("four", "five") union numbers)
        //要查找两个集合中都存在的元素（交集），请使用 intersect()
        println(numbers intersect setOf("two", "one"))
        //要查找另一个集合中不存在的集合元素（差集），请使用 subtract()
        println(numbers subtract setOf("three", "four"))
        //要查找另一个集合中不存在的集合元素（差集），请使用 subtract()
        println(numbers subtract setOf("four", "three"))
    }
}