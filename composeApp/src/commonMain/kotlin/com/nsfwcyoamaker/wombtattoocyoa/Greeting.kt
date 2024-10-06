package com.nsfwcyoamaker.wombtattoocyoa

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}