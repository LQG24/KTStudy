package com.example.kttest.optimize

import org.junit.Test


/**
应该如何选择
如果数据量比较小，可以使用 Iterable。虽然会创建中间结果集，在数据不大的情况下，对性能的影响不会很严重。
如果处理的数据量比较大，Sequence 是最好的选择，因为不会创建中间结果集，内存开销更小。
 */

class IteratorClass {
    @Test
    fun test() {
        val list = mutableListOf<Int>()
        for (i in 0.. 1000){
            list.add(i)
        }

        val last = System.currentTimeMillis()
        val listMode1 = list.map { it + 2 }.filter { it % 2 == 0 }
        println("${System.currentTimeMillis() - last}")
        //Sequence 是 Kotlin 中一个新的概念，用来表示一个延迟计算的集合。
        // Sequence 只存储操作过程，并不处理任何元素，直到遇到终端操作符才开始处理元素，
        // 我们也可以通过 asSequence 扩展函数
        //  toList() 表示终端操作符，filter 、 map 都是中间操作符，返回 Sequence 实例，
        //  当 Sequence 遇到中间操作符时，只是存储操作过程，并不参与计算，直到遇到 toList()。
        val last1 = System.currentTimeMillis()
        val listMode = list.asSequence().map { it + 2 }.filter { it % 2 == 0 }.toList()
        println("${System.currentTimeMillis() - last1}")

    }
}