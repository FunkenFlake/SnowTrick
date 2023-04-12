package com.snowboard.android.snowtrick

class SnowTricks {
    private val _trick = listOf("melon", "indy", "nose", "tail",
        "mute", "slob", "japan", "crail", "method", "stalefish",)
    private val _rotation = listOf(0, 180, 360)
    private val _direction = listOf("BS", "FS")

    fun getRandomTrickAll(trick: List<String> = _trick,
                          rotation: List<Int> = _rotation,
                          direction: List<String> = _direction,): Any {

        val randomTrickAll = listOf(
            direction.random(),
            trick.random().replaceFirstChar { it.uppercase() },
            rotation.random()
        )

        return when {
            0 in randomTrickAll -> randomTrickAll[1]
            else -> randomTrickAll.easyFormat()
        }
    }

    fun checkTrick(
        checkRotation: MutableList<Int> = mutableListOf(0, 180, 360),
        checkGrabs: Boolean = false,
        checkOneEighty: Boolean = false,
        checkThreeSixty: Boolean = false,
    ): List<Int> {

        when {
            checkOneEighty && !checkRotation.contains(180) -> checkRotation += 180
            !checkOneEighty && checkRotation.contains(180) -> checkRotation -= 180
        }

        when {
            checkThreeSixty && !checkRotation.contains(360) -> checkRotation += 360
            !checkThreeSixty && checkRotation.contains(360) -> checkRotation -= 360
        }

        when {
            checkGrabs && !checkRotation.contains(0) -> checkRotation += 0
            !checkGrabs && checkRotation.contains(0) -> checkRotation -= 0
        }

        if (!checkGrabs && !checkOneEighty && !checkThreeSixty) {
            checkRotation += 0
            checkRotation += 180
            checkRotation += 360
        }

        return checkRotation
    }

//            Проверяем, что вернулось из MyDialogFragmen
    fun tripleRotation(fromListDialog: ArrayList<Int>): Triple<Boolean, Boolean, Boolean> {
        val trickOneEighty: Boolean = 1 in fromListDialog
        val trickThreeSixty: Boolean = 2 in fromListDialog
        val trickGrabs: Boolean = 0 in fromListDialog
        return Triple(trickOneEighty, trickThreeSixty, trickGrabs)
    }

}