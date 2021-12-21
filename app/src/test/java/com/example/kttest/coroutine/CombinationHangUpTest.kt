package com.example.kttest.coroutine

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.system.measureTimeMillis

/**
 * 组合挂起函数
 */
class CombinationHangUpTest {
    @Test
    fun test() {
        testMethod1()
        testMethod2()
        lazyAsyncMethod()
    }

    //惰性启动的 async
    private fun lazyAsyncMethod() = runBlocking{
        val time = measureTimeMillis {
            val one = async (start = CoroutineStart.LAZY){  doSomethingUsefulOne() }
            val two = async (start = CoroutineStart.LAZY){ doSomethingUsefulTwo() }

            // 执行一些计算
            one.start() // 启动第一个
            two.start() // 启动第二个
            println("The answer is ${one.await() + two.await()}")
        }

        println("lazyAsyncMethod:Completed in $time ms")
    }

    private fun testMethod2() = runBlocking{
        val time = measureTimeMillis {
            val one = async { doSomethingUsefulOne() }
            val two = async { doSomethingUsefulTwo() }
            println("The answer is ${one.await() + two.await()}")
        }
        println("testMethod2 Completed in $time ms")
    }

    private fun testMethod1() = runBlocking {
        val time = measureTimeMillis {
            val one = doSomethingUsefulOne()
            val two = doSomethingUsefulTwo()
            println("testMethod1 The answer is ${one + two}")
        }
        println("Completed in $time ms")
    }

    private suspend fun doSomethingUsefulOne(): Int {
        delay(1000L) // 假设我们在这里做了一些有用的事
        return 13
    }

    private suspend fun doSomethingUsefulTwo(): Int {
        delay(1000L) // 假设我们在这里也做了一些有用的事
        return 29
    }
}