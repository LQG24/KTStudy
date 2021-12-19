package com.example.kttest

import org.junit.Test

class FilterTest {
    @Test
    fun test() {
        val numbers = listOf("one", "two", "four")
        val newList = numbers.filter { it.length > 3 }
        val filterIndex = numbers.filterIndexed { index, s -> (index != 0) && (s.length < 4) }
        //如果想使用否定条件来过滤集合
        val filterNot = numbers.filterNot { it.length >= 4 }

        val mixNumbers = listOf(null,1 ,"five","six")
        mixNumbers.filterIsInstance<String>().forEach{
            println(it.toUpperCase())
        }
        println(newList)
        println(filterIndex)
        println(filterNot)



        val numberMap = mapOf("key1" to 1, "key2" to 2, "key11" to 20)
        val newMap = numberMap.filter { (key, value) -> key.endsWith("1") && value > 10 }
        println(newMap)
    }
}