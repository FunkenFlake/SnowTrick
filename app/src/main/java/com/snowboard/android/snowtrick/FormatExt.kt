package com.snowboard.android.snowtrick

fun Any.easyFormat() = this.toString().replace("""[,\]\[]""".toRegex(), "")
fun String.upperFirstChar() = this.replaceFirstChar { it.uppercase() }