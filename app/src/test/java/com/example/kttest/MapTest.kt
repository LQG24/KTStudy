package com.example.kttest

import org.junit.Test

class MapTest {
    @Test
    fun test(){
        val numbersMap = mapOf("one" to 1, "two" to 2, "three" to 3)
        println(numbersMap["one"])
        //getOrElse() 与 list 的工作方式相同：
        // 对于不存在的键，其值由给定的 lambda 表达式返回。
        println(numbersMap.getOrElse("four"){4})
        //如果找不到键，则返回指定的默认值
        println(numbersMap.getOrDefault("four", 10))
        println(numbersMap["five"])

        println(numbersMap.keys)
        println(numbersMap.values)


        val numbersMap1 = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key11" to 11)
        val filteredMap = numbersMap1.filter { (key, value) -> key.endsWith("1") && value > 10}
        println(filteredMap)
    }
}