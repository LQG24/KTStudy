package com.example.kttest.coroutine

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.lang.Thread.sleep

class CoroutineTest {
    @Test
    fun test(){
        GlobalScope.launch{ // 在后台启动一个新的协程并继续
            //Suspend function 'delay' should be called only from a coroutine
            // or another suspend function
            //delay 是一个特殊的 挂起函数 ，它不会造成线程阻塞，但是会 挂起 协程，
            // 并且只能在协程中使用。
            delay(1000L)  //非阻塞的等待 1 秒钟（默认时间单位是毫秒）
            println("world!")
        }
        println("Hello")// 协程已在等待时主线程还在继续
//        sleep(2000L)// 阻塞主线程 2 秒钟来保证 JVM 存活

        runBlocking {
            delay(2000L)
        }
    }
}