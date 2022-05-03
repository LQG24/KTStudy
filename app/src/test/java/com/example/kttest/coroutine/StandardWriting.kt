package com.example.kttest.coroutine

import kotlinx.coroutines.*
import org.junit.Test

/**
 *协程基础
 *
 * 1.作用域构建器：
 * runBlocking 与 coroutineScope 可能看起来很类似，
 * 因为它们都会等待其协程体以及所有子协程结束。
 * 主要区别在于，runBlocking 方法会阻塞当前线程来等待，
 * 而 coroutineScope 只是挂起，会释放底层线程用于其他用途。
 * 由于存在这点差异，runBlocking 是常规函数，而 coroutineScope 是挂起函数。
 */
class StandardWriting {

//    @Test
//    fun test0() = runBlocking {
//        doWorld()
//        println("done")
//    }
//
//    private suspend fun doWorld() {
//        //  1. //我们会创建一个顶层协程,虽然它很轻量，但它运行时仍会消耗一些内存资源。如果我们忘记保持对新启动的协程的引用，它还会继续运行。
//        val job = GlobalScope.launch { // 启动一个新协程并保持对这个作业的引用
//            //是一个特殊的 挂起函数 ，它不会造成线程阻塞，但是会挂起协程，并且只能在协程中使用。
//            delay(1000L)
//            println("World!")
//        }
//        println("Hello,")
//        job.join() // 等待直到子协程执行结束
//    }

    //    调用了 runBlocking 的主线程会一直 阻塞 直到 runBlocking 内部的协程执行完毕。
    @Test
    fun test() = runBlocking { // 开始执行主协程// this: CoroutineScope

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