package com.snowboard.android.snowtrick

import android.annotation.SuppressLint
import android.widget.ImageView
import com.snowboard.android.snowtrick.ext.upperFirstChar

private val direction = arrayOf(Direction.BS, Direction.FS)
var rotation = mutableSetOf(Rotation.OneEighty.id, Rotation.ThreeSixty.id)
var stance = Stance.Goofy

@SuppressLint("StaticFieldLeak")
lateinit var snowboardingImg: ImageView

enum class SnowboardTrick(val id: String) {
    Melon("melon") {
        override fun showTrick(): String {
            when (stance) {
                Stance.Goofy -> snowboardingImg.setImageResource(R.drawable.goofy_melon)
                Stance.Regular -> snowboardingImg.setImageResource(R.drawable.regular_melon)
            }
            return getFormatTrick(Melon, rotation)
        }
    },
    Indy("indy") {
        override fun showTrick(): String {
            when (stance) {
                Stance.Goofy -> snowboardingImg.setImageResource(R.drawable.goofy_indy)
                Stance.Regular -> snowboardingImg.setImageResource(R.drawable.regular_indy)
            }
            return getFormatTrick(Indy, rotation)
        }
    },
    Mute("mute") {
        override fun showTrick(): String {
            when (stance) {
                Stance.Goofy -> snowboardingImg.setImageResource(R.drawable.goofy_mute)
                Stance.Regular -> snowboardingImg.setImageResource(R.drawable.regular_mute)
            }
            return getFormatTrick(Mute, rotation)
        }
    },
    Slob("slob") {
        override fun showTrick(): String {
            when (stance) {
                Stance.Goofy -> snowboardingImg.setImageResource(R.drawable.goofy_slob)
                Stance.Regular -> snowboardingImg.setImageResource(R.drawable.regular_slob)
            }
            return getFormatTrick(Slob, rotation)
        }
    },
    Nose("nose") {
        override fun showTrick(): String {
            when (stance) {
                Stance.Goofy -> snowboardingImg.setImageResource(R.drawable.goofy_nose)
                Stance.Regular -> snowboardingImg.setImageResource(R.drawable.regular_nose)
            }
            return getFormatTrick(Nose, rotation)
        }
    },
    Tail("tail") {
        override fun showTrick(): String {
            when (stance) {
                Stance.Goofy -> snowboardingImg.setImageResource(R.drawable.goofy_tail)
                Stance.Regular -> snowboardingImg.setImageResource(R.drawable.regular_tail)
            }
            return getFormatTrick(Tail, rotation)
        }
    },
    Stalefish("stalfish") {
        override fun showTrick(): String {
            when (stance) {
                Stance.Goofy -> snowboardingImg.setImageResource(R.drawable.goofy_stalefish)
                Stance.Regular -> snowboardingImg.setImageResource(R.drawable.regular_stalefish)
            }
            return getFormatTrick(Stalefish, rotation)
        }
    },
    Method("method") {
        override fun showTrick(): String {
            when (stance) {
                Stance.Goofy -> snowboardingImg.setImageResource(R.drawable.goofy_method)
                Stance.Regular -> snowboardingImg.setImageResource(R.drawable.regular_method)
            }
            return getFormatTrick(Method, rotation)
        }
    },
    Japan("japan") {
        override fun showTrick(): String {
            when (stance) {
                Stance.Goofy -> snowboardingImg.setImageResource(R.drawable.goofy_japan)
                Stance.Regular -> snowboardingImg.setImageResource(R.drawable.regular_japan)
            }
            return getFormatTrick(Japan, rotation)
        }
    },
    Crail("crail") {
        override fun showTrick(): String {
            when (stance) {
                Stance.Goofy -> snowboardingImg.setImageResource(R.drawable.goofy_crail)
                Stance.Regular -> snowboardingImg.setImageResource(R.drawable.regular_crail)
            }
            return getFormatTrick(Crail, rotation)
        }
    },
    RocketAir("rocket air") {
        override fun showTrick(): String {
            snowboardingImg.setImageResource(R.drawable.rocket_air)
            return getFormatTrick(RocketAir, rotation)
        }
    },
    Stelmasky("stelmasky") {
        override fun showTrick(): String {
            when (stance) {
                Stance.Goofy -> snowboardingImg.setImageResource(R.drawable.goofy_stelmasky)
                Stance.Regular -> snowboardingImg.setImageResource(R.drawable.regular_stelmasky)
            }
            return getFormatTrick(Stelmasky, rotation)
        }
    },
    RoastBeef("roast beef") {
        override fun showTrick(): String {
            when (stance) {
                Stance.Goofy -> snowboardingImg.setImageResource(R.drawable.goofy_roast_beef)
                Stance.Regular -> snowboardingImg.setImageResource(R.drawable.regular_roast_beef)
            }
            return getFormatTrick(RoastBeef, rotation)
        }
    },
    Suitcase("suitcase") {
        override fun showTrick(): String {
            when (stance) {
                Stance.Goofy -> snowboardingImg.setImageResource(R.drawable.goofy_suitcase)
                Stance.Regular -> snowboardingImg.setImageResource(R.drawable.regular_suitcase)
            }
            return getFormatTrick(Suitcase, rotation)
        }
    },
    CrossRocket("cross rocket") {
        override fun showTrick(): String {
            snowboardingImg.setImageResource(R.drawable.cross_rocket)
            return getFormatTrick(CrossRocket, rotation)
        }
    },
    DoubleTail("double tail") {
        override fun showTrick(): String {
            snowboardingImg.setImageResource(R.drawable.double_tail)
            return getFormatTrick(DoubleTail, rotation)
        }
    },
    ReachAround("reach around") {
        override fun showTrick(): String {
            when (stance) {
                Stance.Goofy -> snowboardingImg.setImageResource(R.drawable.goofy_reach_around)
                Stance.Regular -> snowboardingImg.setImageResource(R.drawable.regular_reach_around)
            }
            return getFormatTrick(ReachAround, rotation)
        }
    };

    abstract fun showTrick(): String

    fun getFormatTrick(grab: SnowboardTrick, rotation: MutableSet<Int>): String {
        return when (val rotationRandom = rotation.random()) {
            Rotation.Zero.id -> grab.id.upperFirstChar()
            else -> "${direction.random()} ${grab.id.upperFirstChar()} $rotationRandom"
            }
    }
    companion object {
        val grabsEasy = arrayOf(Melon, Indy, Mute, Slob, Nose, Tail, Stalefish, Method)
        val grabsMedium = arrayOf(Japan, Crail, RocketAir, Stelmasky, RoastBeef, Suitcase)
        val grabsHard = arrayOf(CrossRocket, DoubleTail, ReachAround, Method)
        var allGrabs = arrayOf<Array<SnowboardTrick>>(grabsEasy, grabsMedium)
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