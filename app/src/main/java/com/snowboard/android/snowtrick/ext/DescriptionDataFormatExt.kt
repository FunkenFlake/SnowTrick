package com.snowboard.android.snowtrick.ext

import android.content.Context
import com.snowboard.android.snowtrick.SnowboardTrick

const val errorUnknownGrab = "error, unknown grab :("

// чтение текста из файла.txt
fun Context.readTextFromAsset(fileName: String): String {
    return assets.open(fileName).bufferedReader().use {
        it.readText()
    }
}

// преобразование прочитанного текста (из readTextFromAsset) в ассоциативный массив
fun String.getFormatTextAssets(): Map<String, String> {
    val textRead = this
        .split("\n")
        .map { it.split("|")}

    val textToMap = textRead.associate { (nameGrab, description) ->
        nameGrab to description
    }

    return textToMap
}

// для определения description(описания) конкретного трюка.
fun Map<String, String>.getDescriptionTricks(grab: SnowboardTrick): String {
    return this.getOrDefault(grab.id, errorUnknownGrab)
}