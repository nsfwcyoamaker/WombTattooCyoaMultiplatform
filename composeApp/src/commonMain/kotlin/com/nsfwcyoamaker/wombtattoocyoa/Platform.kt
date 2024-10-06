package com.nsfwcyoamaker.wombtattoocyoa

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform