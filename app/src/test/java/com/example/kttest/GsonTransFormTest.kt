package com.example.kttest

import com.google.gson.Gson
import org.junit.Test
import java.sql.DriverManager.println

/**
 *
 * @see https://blog.csdn.net/tian2342/article/details/107981722
 *
 * Gson 解析 kotlin设置默认值会失效
 *
 *
 * 因为gson是直接调用默认构造，然后通过反射设置属性值的。
 * 而kotlin的默认值并不是直接设置在属性上的，而是在差异个数的构造方法上
 *
 * 解决办法：
 *
 * 1。实体类默认参数都填上，会自动生成无参构造，所以会有默认值
 * 2。使用Moshi。原因是因为注解会生成对应的adapter，内部使用的是参数对应的构造，
 * 而不是默认无参构造，所以默认值就生效了。
 */
class GsonTransFormTest {
    @Test
    fun test() {
        val user = "{\"age\":10}"
        val user1 = "{\"name\":\"jack\",\"age\":10}"
        val userBean = Gson().fromJson(user, User::class.java).toString()
        val userBean1 = Gson().fromJson(user1, User::class.java).toString()
        println("userBean:${userBean}")
        println("userBean1:${userBean1}")
    }


    data class User(var name: String? = "", var age: Int)
}