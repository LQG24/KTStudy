package com.example.kttest.coroutine

import kotlinx.coroutines.*
import org.junit.Test
/**
 *协程基础
 */
class StandardWriting {

    @Test
    fun test() = runBlocking { // 开始执行主协程
        //1. //我们会创建一个顶层协程,虽然它很轻量，但它运行时仍会消耗一些内存资源。如果我们忘记保持对新启动的协程的引用，它还会继续运行。
//        val job = GlobalScope.launch { // 启动一个新协程并保持对这个作业的引用

//            delay(1000L)
//            println("World!")
//        }
//        println("Hello,")
//        job.join() // 等待直到子协程执行结束

        //2.
//        launch {
//            delay(1000L)
//            println("World!")
//        }
//        //因为外部协程（示例中的 runBlocking）直到在其作用域中启动的所有协程都执行完毕后才会结束
//        println("Hello,")

        //3.
        launch {
            delay(200L)
            println("Task from runBlocking")
        }

        //除了由不同的构建器提供协程作用域之外，还可以使用 coroutineScope 构建器声明自己的作用域。
        // 它会创建一个协程作用域并且在所有已启动子协程执行完毕之前不会结束。
        coroutineScope { // 创建一个协程作用域
            launch {
                taskLaunch()
            }

            delay(100L)
            println("Task from coroutine scope") // 这一行会在内嵌 launch 之前输出
        }

        println("Coroutine scope is over") // 这一行在内嵌 launch 执行完毕后才输出

    }

    //提取函数重构
    //suspend 修饰符的新函数, 挂起函数
    private suspend fun taskLaunch(){
        delay(500L)
        println("Task from nested launch")
    }
}