package com.example.kttest

import org.junit.Test

/**
 * 取单个元素
 */
class SingleElement {
    @Test
    fun test() {
        val numbers = linkedSetOf("one", "two", "three", "four", "five")
        println(numbers.elementAt(3))
//        当指定位置超出集合范围时，elementAtOrNull() 返回 null。
        println(numbers.elementAtOrNull(5))
        //当使用一个越界位置来调用时，elementAtOrElse() 返回对给定值调用该 lambda 表达式的结果。
        println(numbers.elementAtOrElse(5) { index -> "The value for index $index is undefined" })
//        如果没有元素与谓词匹配，两个函数都会抛异常。 为了避免它们，请改用 firstOrNull() 和 lastOrNull()：
        println(numbers.firstOrNull { it.length > 6 })
        //随机取元素
        println("random:${numbers.random()}")
        //如需检查集合中某个元素的存在，可以使用 contains() 函数。
        // 如果存在一个集合元素等于（equals()）函数参数，那么它返回 true。
        // 你可以使用 in 关键字以操作符的形式调用 contains()
        println("contains:${numbers.contains("four")}")
        println("zero" in numbers)
        println("four" in numbers)
        //如需一次检查多个实例的存在，可以使用这些实例的集合作为参数调用 containsAll()
        println("containsAll:"+numbers.containsAll(listOf("four", "two")))
        println("containsAll:"+numbers.containsAll(listOf("one", "zero")))


        //自然排序顺序
        val sortNumbers = sortedSetOf("one", "two", "three", "four")
        println(sortNumbers.elementAt(0))
        println(sortNumbers)
    }
}