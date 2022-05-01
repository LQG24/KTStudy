package com.example.kttest.coroutine

import kotlinx.coroutines.delay

class FlowTest4 {
    suspend fun performRequest(request:Int):String{
        delay(1000)
        return "response "
    }
}