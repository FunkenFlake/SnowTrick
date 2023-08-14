package com.snowboard.android.snowtrick

import android.content.Context
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.FileReader
import java.io.InputStreamReader
import java.io.FileInputStream


//val descriptionDataGrabs = File("files/description-grabs.txt").readText()
val someDes = FileInputStream("description-grabs.txt")
val someStream = InputStreamReader(this.open("sldkfj"))
//val someReadDes = BufferedReader(FileReader(someDes))
fun readFromAssets(context: Context, fileName: String): String {
    val readerFromAssets = BufferedReader(InputStreamReader(context.assets.open(fileName), "UTF-8"))
    val sb = StringBuilder()
    var line: String? = readerFromAssets.readLine()
    while (line != null) {
        sb.append(line)
        line = readerFromAssets.readLine()
    }
    readerFromAssets.close()
    return sb.toString()
}









//    .readText()
//    .split("\n")
//    .map { it.split("|")}

//val grabDescriptionTuple = descriptionDataGrabs.associate { (nameGrab, description) ->
//    nameGrab to description
//}

//fun getDescriptionGrab(grab: SnowboardTrick): String {
//    return grabDescriptionTuple.getOrDefault(grab.id, "Unknown grabs :(")
//}