package com.example.kttest.coroutine

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.system.measureTimeMillis

/**
 * 组合挂起函数
 *
 * 函数内部发生了错误，并且它抛出了一个异常， 所有在作用域中启动的协程都会被取消。
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
            //async 就类似于 launch。
            // 它启动了一个单独的协程，这是一个轻量级的线程并与其它所有的协程一起并发的工作。
            //不同之处在于 launch 返回一个 Job 并且不附带任何结果值，
            // 而 async 返回一个 Deferred —— 一个轻量级的非阻塞 future，
            // 这代表了一个将会在稍后提供结果的 promise。你可以使用 .
            // await() 在一个延期的值上得到它的最终结果， 但是 Deferred 也是一个 Job，所以如果需要的话，你可以取消它。
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