package com.example.kttest

import org.junit.Test

/**
 *取集合的一部分
 */
class PartOfTest {
    @Test
    fun test(){
        //slice() 返回具有给定索引的集合元素列表
        val numbers = listOf("one","two","three","four","five","six")
        println(numbers.slice(1..3))
        println(numbers.slice(0..4 step 2))

        //要从头开始获取指定数量的元素，请使用 take() 函数
        println(numbers.take(3))
        //要从尾开始获取指定数量的元素，请使用 takeLast()
        println(numbers.takeLast(3))
        //要从头或从尾去除给定数量的元素，请调用 drop() 或 dropLast() 函数。
        println(numbers.drop(1))
        println(numbers.dropLast(5))

        //要将集合分解为给定大小的“块”，请使用 chunked() 函数
        println(numbers.chunked(4))

    }
}