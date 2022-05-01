package com.example.kttest

import org.junit.Test

/**
 * kotlin == 和===的区别
 */
class EqualSignTest {
    @Test
    fun test(){
        //总体来说kotlin中==比较的是数值是否相等,
        // 而===比较的是两个对象的地址是否相等
        val a: Int = 999
        val b: Int? = a
        val c: Int? = a
        println(b == c)    //true
        println(b === c)   //false


        val d: Int = a
        val e: Int = a
        println(d == e)     // true
        println(d === e)    // true
    }
}