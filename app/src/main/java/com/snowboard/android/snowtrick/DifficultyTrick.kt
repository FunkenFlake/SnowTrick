package com.snowboard.android.snowtrick

class DifficultyTrick {

    private val _easyTrick = listOf("mute", "slob", "indy", "nose",
        "tail", "stalfish", "melon", "method",
    )

    private val _mediumTrick = listOf("japan", "crail", "rocket air",
        "stelmasky", "roast beef", "suitcase",
    )

    private val _hardTrick = listOf("cross rocket", "double tail", "reach around", "method")

    fun checkDifficultyGrab(checkEasyTrick: Boolean = false,
                            checkMediumTrick: Boolean = false,
                            checkHardTrick: Boolean = false,
    ): List<String> {
        return when {
            checkEasyTrick && !checkMediumTrick && !checkHardTrick -> _easyTrick
            !checkEasyTrick && checkMediumTrick && !checkHardTrick -> _mediumTrick
            !checkEasyTrick && !checkMediumTrick && checkHardTrick -> _hardTrick
            checkEasyTrick && checkMediumTrick && !checkHardTrick -> _easyTrick + _mediumTrick
            checkEasyTrick && !checkMediumTrick && checkHardTrick -> _easyTrick + _hardTrick
            !checkEasyTrick && checkMediumTrick && checkHardTrick -> _mediumTrick + _hardTrick
            else -> _easyTrick + _mediumTrick + _hardTrick
        }
    }

//            Проверяем, что вернулось из CheckDifficultyFragment
    fun tripleDifficulty(fromListDialogDifficulty: ArrayList<Int>) : Triple<Boolean, Boolean, Boolean> {
        val easyTrick: Boolean = 0 in fromListDialogDifficulty
        val mediumTrick: Boolean = 1 in fromListDialogDifficulty
        val hardTrick: Boolean = 2 in fromListDialogDifficulty
        return Triple(easyTrick, mediumTrick, hardTrick)
    }
}