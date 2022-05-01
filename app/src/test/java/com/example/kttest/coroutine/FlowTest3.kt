package com.example.kttest.coroutine

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Test

/**
 * 流构建器
 * 还有其他构建器使流的声明更简单：
 *1.flowOf 构建器定义了一个发射固定值集的流。
 *2.使用 .asFlow() 扩展函数，可以将各种集合与序列转换为流。
 */
class FlowTest3 {
    @Test
    fun test() = runBlocking {
        // 将一个整数区间转化为流
        (1..3).asFlow().collect { value -> println(value) }
    }
}