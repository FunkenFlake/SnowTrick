package com.snowboard.android.snowtrick

import android.content.Context

class FormatDescriptionTrick

fun Context.readTextFromAsset(fileName: String): String {
    return assets.open(fileName).bufferedReader().use {
        it.readText()
    }
}

fun formatTextAsset(text: String): Map<String, String> {

    val textRead = text
        .split("\n")
        .map { it.split("|") }

    val textToMap = textRead.associate { (nameGrab, description) ->
            nameGrab to description
    }

    return textToMap
}

fun getDescriptionGrab(grab: SnowboardTrick): String {
    return formatTextAsset()


    TODO()
}

