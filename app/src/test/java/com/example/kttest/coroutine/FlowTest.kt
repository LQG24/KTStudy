package com.example.kttest.coroutine

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

class FlowTest {
    @Test
    fun test() = runBlocking {
        // 启动并发的协程以验证主线程并未阻塞
        launch {
            for (k in 1..3) {
                println("I'm not blocked $k")
                delay(100)
            }
        }
        // 收集这个流
        simple().collectLatest { value ->  println(value)
            delay( 500)
        println("done $value")}

    }

    private fun simple(): Flow<Int> = flow {// 流构建器
        for (i in 1..3) {
            delay(100)// 假装我们在这里做了一些有用的事情
            emit(i) // 发送下一个值
        }
    }
}