package com.snowboard.android.snowtrick

import android.annotation.SuppressLint
import android.widget.ImageView

private val direction = arrayOf(Direction.BS, Direction.FS)
private val rotation = setOf(Rotation.OneEighty.id, Rotation.ThreeSixty.id)
var stance = arrayOf(Stance.Goofy, Stance.Regular)

@SuppressLint("StaticFieldLeak")
lateinit var snowboardingImg: ImageView

enum class SnowboardTrick {
    Melon {
        override fun getTrick(): String {
            snowboardingImg.setImageResource(R.drawable.goofy_melon)
            return "${direction.random()} $Melon ${rotation.random()}"
        }
    },
    Indy {
        override fun getTrick(): String {
            snowboardingImg.setImageResource(R.drawable.goofy_indy)
            return "${direction.random()} $Indy ${rotation.random()}"
        }
    },
    Mute {
        override fun getTrick(): String {
            snowboardingImg.setImageResource(R.drawable.goofy_mute)
            return "${direction.random()} $Mute ${rotation.random()}"
        }
    },
    Slob {
        override fun getTrick(): String {
            snowboardingImg.setImageResource(R.drawable.goofy_slob)
            return "${direction.random()} $Slob ${rotation.random()}"
        }
    },
    Nose {
        override fun getTrick(): String {
            snowboardingImg.setImageResource(R.drawable.goofy_nose)
            return "${direction.random()} $Nose ${rotation.random()}"
        }
    },
    Tail {
        override fun getTrick(): String {
            snowboardingImg.setImageResource(R.drawable.goofy_tail)
            return "${direction.random()} $Tail ${rotation.random()}"
        }
    },
    Stalefish {
        override fun getTrick(): String {
            snowboardingImg.setImageResource(R.drawable.goofy_stalefish)
            return "${direction.random()} $Stalefish ${rotation.random()}"
        }
    },
    Method {
        override fun getTrick(): String {
            snowboardingImg.setImageResource(R.drawable.goofy_method)
            return "${direction.random()} $Method ${rotation.random()}"
        }
    },
    Japan {
        override fun getTrick(): String {
            snowboardingImg.setImageResource(R.drawable.goofy_japan)
            return "${direction.random()} $Japan ${rotation.random()}"
        }
    },
    Crail {
        override fun getTrick(): String {
            snowboardingImg.setImageResource(R.drawable.goofy_crail)
            return "${direction.random()} $Crail ${rotation.random()}"
        }
    },
    RocketAir {
        override fun getTrick(): String {
            snowboardingImg.setImageResource(R.drawable.rocket_air)
            return "${direction.random()} $RocketAir ${rotation.random()}"
        }
    },
    Stelmasky {
        override fun getTrick(): String {
            snowboardingImg.setImageResource(R.drawable.goofy_stelmasky)
            return "${direction.random()} $Stelmasky ${rotation.random()}"
        }
    },
    RoastBeef {
        override fun getTrick(): String {
            snowboardingImg.setImageResource(R.drawable.goofy_roast_beef)
            return "${direction.random()} $RoastBeef ${rotation.random()}"
        }
    },
    Suitcase {
        override fun getTrick(): String {
            snowboardingImg.setImageResource(R.drawable.goofy_suitcase)
            return "${direction.random()} $Suitcase ${rotation.random()}"
        }
    },
        CrossRocket {
        override fun getTrick(): String {
            snowboardingImg.setImageResource(R.drawable.cross_rocket)
            return "${direction.random()} $CrossRocket ${rotation.random()}"
        }
    },
        DoubleTail {
        override fun getTrick(): String {
            snowboardingImg.setImageResource(R.drawable.double_tail)
            return "${direction.random()} $DoubleTail ${rotation.random()}"
        }
    },
        ReachAround {
        override fun getTrick(): String {
            snowboardingImg.setImageResource(R.drawable.goofy_reach_around)
            return "${direction.random()} $ReachAround ${rotation.random()}"
        }
    },
    ;

    abstract fun getTrick(): String

    companion object {
        val grabsEasy = arrayOf(Melon, Indy, Mute, Slob, Nose, Tail, Stalefish, Method)
        val grabsMedium = arrayOf(Japan, Crail, RocketAir, Stelmasky, RoastBeef, Suitcase)
        val grabsHard = arrayOf(CrossRocket, DoubleTail, ReachAround, Method)


    }
}

enum class Direction {
    BS,
    FS,
}

enum class Rotation(val id: Int) {
    OneEighty(180),
    ThreeSixty(360),
}

enum class Stance {
    Goofy,
    Regular,
}