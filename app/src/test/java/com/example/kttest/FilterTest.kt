package com.example.kttest

import org.junit.Test

class FilterTest {
    @Test
    fun test() {
        val numbers = listOf("one", "two", "four")
        val newList = numbers.filter { it.length > 3 }
        println(newList)
        //它接受一个带有两个参数的谓词：元素的索引和元素的值。
        val filterIndex = numbers.filterIndexed { index, s -> (index != 0) && (s.length < 4) }
        println(filterIndex)
        //如果想使用否定条件来过滤集合
        val filterNot = numbers.filterNot { it.length >= 4 }
        println(filterNot)

        val mixNumbers = listOf(null,1 ,"five","six")
        //返回给定类型的集合元素
        mixNumbers.filterIsInstance<String>().forEach{
            println(it.toUpperCase())
        }






        val numberMap = mapOf("key1" to 1, "key2" to 2, "key11" to 20)
        val newMap = numberMap.filter { (key, value) -> key.endsWith("1") && value > 10 }
        println(newMap)
    }
}