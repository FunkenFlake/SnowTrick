package com.snowboard.android.snowtrick

fun Any.easyFormat() = this.toString().replace("""[,\]\[]""".toRegex(), "")
fun String.upperFirstChar() = this.replaceFirstChar { it.uppercase() }

val imageGrab = mapOf(
    "goofy_reach_around" to snowboardingImg.setImageResource(R.drawable.goofy_reach_around)
)