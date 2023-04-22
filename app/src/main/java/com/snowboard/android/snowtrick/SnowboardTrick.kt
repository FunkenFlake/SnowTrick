package com.snowboard.android.snowtrick

import android.annotation.SuppressLint
import android.widget.ImageView

private val direction = arrayOf(Direction.BS, Direction.FS)
var rotation = mutableSetOf(Rotation.OneEighty.id, Rotation.ThreeSixty.id)
var stance = Stance.Goofy

@SuppressLint("StaticFieldLeak")
lateinit var snowboardingImg: ImageView

enum class SnowboardTrick(private val id: String) {
    Melon("melon") {
        override fun getTrick(): String {
            when (stance) {
                Stance.Goofy -> snowboardingImg.setImageResource(R.drawable.goofy_melon)
                Stance.Regular -> snowboardingImg.setImageResource(R.drawable.regular_melon)
            }
            return returnGrab(Melon, rotation)
        }
    },
    Indy("indy") {
        override fun getTrick(): String {
            when (stance) {
                Stance.Goofy -> snowboardingImg.setImageResource(R.drawable.goofy_indy)
                Stance.Regular -> snowboardingImg.setImageResource(R.drawable.regular_indy)
            }
            return returnGrab(Indy, rotation)
        }
    },
    Mute("mute") {
        override fun getTrick(): String {
            when (stance) {
                Stance.Goofy -> snowboardingImg.setImageResource(R.drawable.goofy_mute)
                Stance.Regular -> snowboardingImg.setImageResource(R.drawable.regular_mute)
            }
            return returnGrab(Mute, rotation)
        }
    },
    Slob("slob") {
        override fun getTrick(): String {
            when (stance) {
                Stance.Goofy -> snowboardingImg.setImageResource(R.drawable.goofy_slob)
                Stance.Regular -> snowboardingImg.setImageResource(R.drawable.regular_slob)
            }
            return returnGrab(Slob, rotation)
        }
    },
    Nose("nose") {
        override fun getTrick(): String {
            when (stance) {
                Stance.Goofy -> snowboardingImg.setImageResource(R.drawable.goofy_nose)
                Stance.Regular -> snowboardingImg.setImageResource(R.drawable.regular_nose)
            }
            return returnGrab(Nose, rotation)
        }
    },
    Tail("tail") {
        override fun getTrick(): String {
            when (stance) {
                Stance.Goofy -> snowboardingImg.setImageResource(R.drawable.goofy_tail)
                Stance.Regular -> snowboardingImg.setImageResource(R.drawable.regular_tail)
            }
            return returnGrab(Tail, rotation)
        }
    },
    Stalefish("stalfish") {
        override fun getTrick(): String {
            when (stance) {
                Stance.Goofy -> snowboardingImg.setImageResource(R.drawable.goofy_stalefish)
                Stance.Regular -> snowboardingImg.setImageResource(R.drawable.regular_stalefish)
            }
            return returnGrab(Stalefish, rotation)
        }
    },
    Method("method") {
        override fun getTrick(): String {
            when (stance) {
                Stance.Goofy -> snowboardingImg.setImageResource(R.drawable.goofy_method)
                Stance.Regular -> snowboardingImg.setImageResource(R.drawable.regular_method)
            }
            return returnGrab(Method, rotation)
        }
    },
    Japan("japan") {
        override fun getTrick(): String {
            when (stance) {
                Stance.Goofy -> snowboardingImg.setImageResource(R.drawable.goofy_japan)
                Stance.Regular -> snowboardingImg.setImageResource(R.drawable.regular_japan)
            }
            return returnGrab(Japan, rotation)
        }
    },
    Crail("crail") {
        override fun getTrick(): String {
            when (stance) {
                Stance.Goofy -> snowboardingImg.setImageResource(R.drawable.goofy_crail)
                Stance.Regular -> snowboardingImg.setImageResource(R.drawable.regular_crail)
            }
            return returnGrab(Crail, rotation)
        }
    },
    RocketAir("rocket air") {
        override fun getTrick(): String {
            snowboardingImg.setImageResource(R.drawable.rocket_air)
            return returnGrab(RocketAir, rotation)
        }
    },
    Stelmasky("stelmasky") {
        override fun getTrick(): String {
            when (stance) {
                Stance.Goofy -> snowboardingImg.setImageResource(R.drawable.goofy_stelmasky)
                Stance.Regular -> snowboardingImg.setImageResource(R.drawable.regular_stelmasky)
            }
            return returnGrab(Stelmasky, rotation)
        }
    },
    RoastBeef("roast beef") {
        override fun getTrick(): String {
            when (stance) {
                Stance.Goofy -> snowboardingImg.setImageResource(R.drawable.goofy_roast_beef)
                Stance.Regular -> snowboardingImg.setImageResource(R.drawable.regular_roast_beef)
            }
            return returnGrab(RoastBeef, rotation)
        }
    },
    Suitcase("suitcase") {
        override fun getTrick(): String {
            when (stance) {
                Stance.Goofy -> snowboardingImg.setImageResource(R.drawable.goofy_suitcase)
                Stance.Regular -> snowboardingImg.setImageResource(R.drawable.regular_suitcase)
            }
            return returnGrab(Suitcase, rotation)
        }
    },
    CrossRocket("cross rocket") {
        override fun getTrick(): String {
            snowboardingImg.setImageResource(R.drawable.cross_rocket)
            return returnGrab(CrossRocket, rotation)
        }
    },
    DoubleTail("double tail") {
        override fun getTrick(): String {
            snowboardingImg.setImageResource(R.drawable.double_tail)
            return returnGrab(DoubleTail, rotation)
        }
    },
    ReachAround("reach around") {
        override fun getTrick(): String {
            when (stance) {
                Stance.Goofy -> snowboardingImg.setImageResource(R.drawable.goofy_reach_around)
                Stance.Regular -> snowboardingImg.setImageResource(R.drawable.regular_reach_around)
            }
            return returnGrab(ReachAround, rotation)
        }
    };

    abstract fun getTrick(): String

    fun returnGrab(grab: SnowboardTrick, rotation: MutableSet<Int>): String {
        return when (val rotationRandom = rotation.random()) {
            Rotation.Zero.id -> grab.id.upperFirstChar()
            else -> "${direction.random()} ${grab.id.upperFirstChar()} $rotationRandom"
        }
    }

    companion object {
        val grabsEasy = arrayOf(Melon, Indy, Mute, Slob, Nose, Tail, Stalefish, Method)
        val grabsMedium = arrayOf(Japan, Crail, RocketAir, Stelmasky, RoastBeef, Suitcase)
        val grabsHard = arrayOf(CrossRocket, DoubleTail, ReachAround, Method)
        var allGrabs = arrayOf<Array<SnowboardTrick>>(grabsEasy)
    }
}

enum class Direction {
    BS,
    FS,
}

enum class Rotation(val id: Int) {
    Zero(0),
    OneEighty(180),
    ThreeSixty(360),
}

enum class Stance {
    Goofy,
    Regular,
}