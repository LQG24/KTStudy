package com.example.kttest.coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.math.log

/**
 * 异步流
 *
 * Kotlin 流（Flow）异步返回多个计算好的值
 */
class AsyncStreamTest {
    @Test
    fun test() {
//        testMethod1()
//        testMethod2()
//        testMethod3()
//        testMethod4()
//        testMethod5()
//        testMethod6()
//        testMethod7()
        testMethod8()
    }




    //末端操作符是在流上用于启动流收集的挂起函数。
    // collect 是最基础的末端操作符，但是还有另外一些更方便使用的末端操作符：
    //转化为各种集合，例如 toList 与 toSet。
    //获取第一个（first）值与确保流发射单个（single）值的操作符。
    //使用 reduce 与 fold 将流规约到单个值。
    private fun testMethod6() = runBlocking{
        val sum = (1..5).asFlow()
            .map { it*it }// 数字 1 至 5 的平方
            .reduce{a,b-> a+b}
        println("testMethod6:${sum}")
    }

    /**
     * 限长操作符：
     * 限长过渡操作符（例如 take）在流触及相应限制的时候会将它的执行取消。
     * 协程中的取消操作总是通过抛出异常来执行，
     * 这样所有的资源管理函数（如 try {...} finally {...} 块）会在取消的情况下正常运行：
     */
    private fun testMethod5() = runBlocking {
        numbers()
            .take(2) // 只获取前两个
            .collect { value -> println(value) }
    }

    //限长过渡操作符（例如 take）在流触及相应限制的时候会将它的执行取消。
    // 协程中的取消操作总是通过抛出异常来执行，这样所有的资源管理函数
    // （如 try {...} finally {...} 块）会在取消的情况下正常运行：
    private fun numbers(): Flow<Int> = flow {
        try {
            emit(1)
            emit(2)
            println("This line will not execute")
            emit(3)
        } catch (e: Exception) {
            println("exception:${e.message}")
        } finally {
            println("Finally in numbers")
        }
    }

    /**
     * 转换操作符
     *
     * 在流转换操作符中，最通用的一种称为 transform。
     * 它可以用来模仿简单的转换，例如 map 与 filter，以及实施更复杂的转换。
     * 使用 transform 操作符，我们可以 发射 任意值任意次。
     *比如说，使用 transform 我们可以在执行长时间运行的异步请求之前发射一个字符串并跟踪这个响应：
     */
    private fun testMethod4() = runBlocking {
        (1..3).asFlow() // 一个请求流
            .transform { request ->
                emit("Making request $request")
                emit(performRequest(request))
            }
            .collect { response -> println(response) }
    }

    /**
     * 名为 flow 的 Flow 类型构建器函数。
     *flow { ... } 构建块中的代码可以挂起。
     *函数 simple 不再标有 suspend 修饰符。
     *流使用 emit 函数 发射 值。
     *流使用 collect 函数 收集 值。
     */


    /**
     * 可以使用操作符转换流，就像使用集合与序列一样。
     * 过渡操作符应用于上游流，并返回下游流。 这些操作符也是冷操作符，就像流一样。
     * 这类操作符本身不是挂起函数。它运行的速度很快，返回新的转换流的定义。
     *
     * 基础的操作符拥有相似的名字，比如 map 与 filter。
     * 流与序列的主要区别在于这些操作符中的代码可以调用挂起函数。
     */
    private fun testMethod3() = runBlocking {
        (1..3).asFlow() // 一个请求流
            .map { request -> performRequest(request) }
            .collect { response -> println(response) }
    }

    private suspend fun performRequest(request: Int): String {
        delay(1000) // 模仿长时间运行的异步工作
        return "response $request"
    }

    private fun testMethod1() {
        simple().forEach { value ->
            println(value)
        }
    }

    private fun testMethod2() {
        simpleSequence().forEach { value ->
            println("testMethod2:${value}")
        }
    }


    private fun simple(): List<Int> = listOf(1, 2, 3)

    private fun simpleSequence(): Sequence<Int> = sequence { // 序列构建器
        for (i in 1..3) {
            Thread.sleep(100) // 假装我们正在计算
            yield(i) // 产生下一个值
        }
    }

    private fun testMethod7() = runBlocking{
        (1..5).asFlow().filter {
            println("Filter $it")
            it % 2 == 0
        }.map {
            println("Map $it")
            "string $it"
        }.collect {
            println("collect $it")
        }

        println("current:${Thread.currentThread().name}")
    }

    //就像 Kotlin 标准库中的 Sequence.zip 扩展函数一样，
    // 流拥有一个 zip 操作符用于组合两个流中的相关值：
    private fun testMethod8() = runBlocking{

        val nums = (1..3).asFlow() // 数字 1..3
        val strs = flowOf("one", "two", "three") // 字符串
        nums.zip(strs) { a, b -> "$a -> $b" } // 组合单个字符串
            .collect { println("zip:${it}") } // 收集并打印
    }

}