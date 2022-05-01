package com.example.kttest

import org.junit.Test

class SortText {
    @Test
    fun test(){
        val numbers = listOf("one","two","three","four")
        println(numbers.groupBy { it.first().toUpperCase() })
        println(numbers.groupBy ( keySelector = {it.first()},valueTransform= {it.toUpperCase()}))
        println(numbers.groupingBy { it.first() }.eachCount())

        val adc = null
        val bcd = "abc"
        if(adc == bcd){
            println("value equals")
        }else{
            println("not equals")
        }
    }
}