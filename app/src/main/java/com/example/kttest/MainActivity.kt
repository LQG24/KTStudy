package com.example.kttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.lang.Thread.sleep

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.tv_test).setOnClickListener {
            testMethod1()

        }

        findViewById<TextView>(R.id.tv_test2).setOnClickListener {
            for (i in 0 until 20) {
                testMethod2()
            }
        }
    }

    private fun testMethod2() {
        sleep(50)
    }

    private fun testMethod1() {
        sleep(1000)
    }
}