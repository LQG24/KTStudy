package com.example.kttest.coroutine

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Test

/**
 * 这是返回一个流的 simple 函数没有标记 suspend 修饰符的主要原因。
 * 通过它自己，simple() 调用会尽快返回且不会进行任何等待。
 * 该流在每次收集的时候启动，
 * 这就是为什么当我们再次调用 collect 时我们会看到“Flow started”。
 */
class FlowTest1 {
    private fun simple(): Flow<Int> = flow {
        println("Flow started")
        for (i in 1..3){
            delay(100)
            emit(i)
        }
    }

    @Test
    fun main() = runBlocking<Unit> {
        println("current thread:${Thread.currentThread().name}")
        println("Calling simple function...")
        val flow = simple()
        println("Calling collect...")
        flow.collect { value ->  println(value)}
        println("Calling collect again...")
        flow.collect { value -> println(value) }
    }
}