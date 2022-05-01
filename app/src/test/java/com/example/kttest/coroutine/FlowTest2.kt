package com.example.kttest.coroutine

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull
import org.junit.Test

/**
 * 流取消基础
 *流采用与协程同样的协作取消。
 * 像往常一样，流的收集可以在当流在一个可取消的挂起函数（例如 delay）中挂起的时候取消。
 * 以下示例展示了当 withTimeoutOrNull 块中代码在运行的时候流是如何在超时的情况下取消并停止执行其代码的：
 */
class FlowTest2 {
    private fun simple(): Flow<Int> = flow {
        for (i in 1..3) {
            delay(100)
            println("Emitting $i")
            emit(i)
        }
    }

    @Test
    fun main() = runBlocking<Unit> {
        withTimeoutOrNull(250) { // 在 250 毫秒后超时
            simple().collect { value ->
                println(value)
            }
        }
        println("Done")
    }
}