package com.example.kttest.coroutine

import kotlinx.coroutines.*
import org.junit.Test

class HandUpTest {
    @Test
    fun test(): Unit = runBlocking{
        launch{
           timeConsume()
            println("launch thread name ${Thread.currentThread().name}")
        }
    }

    private suspend fun timeConsume(){
        println("thread name ${Thread.currentThread().name}")
        withContext(Dispatchers.IO){
            println("withContext thread name ${Thread.currentThread().name}")
            delay(100)
            println("delay")
        }
    }
}