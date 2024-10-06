package com.nsfwcyoamaker.wombtattoocyoa

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Womb Tattoo Cyoa",
    ) {
        App()
    }
}