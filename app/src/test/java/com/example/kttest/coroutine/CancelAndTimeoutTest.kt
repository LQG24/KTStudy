package com.example.kttest.coroutine

import kotlinx.coroutines.*
import org.junit.Test

class CancelAndTimeoutTest {
    @Test
    fun test() = runBlocking {
        //1.
//        val job = launch {
//            repeat(1000) { i ->
//                println("job: I'm sleeping $i ...")
//                delay(500L)
//            }
//        }
//
//        delay(1300L) // 延迟一段时间
//        println("main: I'm tired of waiting!")
//        //一旦 main 函数调用了 job.cancel，我们在其它的协程中就看不到任何输出，因为它被取消了。
//        // 这里也有一个可以使 Job 挂起的函数 cancelAndJoin 它合并了对 cancel 以及 join 的调用。
//        job.cancel() // 取消该作业
////        job.join() // 等待作业执行结束
//        println("main: Now I can quit.")
//
//        canCancelTask()

        //2.使计算代码可取消
//        val startTime = System.currentTimeMillis()
//        val job = launch(Dispatchers.Default) {
//            var nextPrintTime = startTime
//            var i = 0
//            //显式的检查取消状态。
//            while (isActive) { // 可以被取消的计算循环
//                // 每秒打印消息两次
//                if (System.currentTimeMillis() >= nextPrintTime) {
//                    println("job: I'm sleeping ${i++} ...")
//                    nextPrintTime += 500L
//                }
//            }
//        }
//        delay(1300L) // 等待一段时间
//        println("main: I'm tired of waiting!")
//        job.cancelAndJoin() // 取消该作业并等待它结束
//        println("main: Now I can quit.")
//    }

        //3.运行不能取消的代码块
        val job = launch {
            try {
                repeat(1000) { i ->
                    println("job: I'm sleeping $i ...")
                    delay(500L)
                }
            } finally {
                withContext(NonCancellable) {
                    println("job: I'm running finally")
                    delay(1000L)
                    println("job: And I've just delayed for 1 sec because I'm non-cancellable")
                }
            }
        }
        delay(1300L) // 延迟一段时间
        println("main: I'm tired of waiting!")
        job.cancelAndJoin() // 取消该作业并等待它结束
        println("main: Now I can quit.")


        doTimeOutTask()

    }

    //在实践中绝大多数取消一个协程的理由是它有可能超时。
    private suspend fun doTimeOutTask(){
        val result = withTimeoutOrNull(1300L) {
            repeat(1000) { i ->
                println("I'm sleeping $i ...")
                delay(500L)
            }
            "Done" // 在它运行得到结果之前取消它
        }
        println("Result is $result")
    }
}