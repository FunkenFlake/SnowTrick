package com.snowboard.android.snowtrick

import android.annotation.SuppressLint
import android.widget.ImageView

private val direction = arrayOf(Direction.BS, Direction.FS)
var rotation = mutableSetOf(Rotation.OneEighty.id, Rotation.ThreeSixty.id)
var stance = Stance.Goofy

@SuppressLint("StaticFieldLeak")
lateinit var snowboardingImg: ImageView

enum class SnowboardTrick(val id: String, val description: String) {
    Melon("melon", "Схватить передней рукой\nВытянуться в FS, так будет красивей") {
        override fun showTrick(): String {
            when (stance) {
                Stance.Goofy -> snowboardingImg.setImageResource(R.drawable.goofy_melon)
                Stance.Regular -> snowboardingImg.setImageResource(R.drawable.regular_melon)
            }
            return getFormatTrick(Melon, rotation)
        }
    },
    Indy("indy", "Взять задней рукой\nДля красоты лучше развернуться в BS") {
        override fun showTrick(): String {
            when (stance) {
                Stance.Goofy -> snowboardingImg.setImageResource(R.drawable.goofy_indy)
                Stance.Regular -> snowboardingImg.setImageResource(R.drawable.regular_indy)
            }
            return getFormatTrick(Indy, rotation)
        }
    },
    Mute("mute","Передней рукой\nВыглядит оригинально при вращении в BS") {
        override fun showTrick(): String {
            when (stance) {
                Stance.Goofy -> snowboardingImg.setImageResource(R.drawable.goofy_mute)
                Stance.Regular -> snowboardingImg.setImageResource(R.drawable.regular_mute)
            }
            return getFormatTrick(Mute, rotation)
        }
    },
    Slob("slob","Передней рукой\nВыпрямить заднюю ногу") {
        override fun showTrick(): String {
            when (stance) {
                Stance.Goofy -> snowboardingImg.setImageResource(R.drawable.goofy_slob)
                Stance.Regular -> snowboardingImg.setImageResource(R.drawable.regular_slob)
            }
            return getFormatTrick(Slob, rotation)
        }
    },
    Nose("nose","Передней рукой\nЛучше подогнуть переднее колено") {
        override fun showTrick(): String {
            when (stance) {
                Stance.Goofy -> snowboardingImg.setImageResource(R.drawable.goofy_nose)
                Stance.Regular -> snowboardingImg.setImageResource(R.drawable.regular_nose)
            }
            return getFormatTrick(Nose, rotation)
        }
    },
    Tail("tail","Задней рукой\nЛучше согнуть заднее колено") {
        override fun showTrick(): String {
            when (stance) {
                Stance.Goofy -> snowboardingImg.setImageResource(R.drawable.goofy_tail)
                Stance.Regular -> snowboardingImg.setImageResource(R.drawable.regular_tail)
            }
            return getFormatTrick(Tail, rotation)
        }
    },
    Stalefish("stalfish","Задней рукой\nНе через ноги") {
        override fun showTrick(): String {
            when (stance) {
                Stance.Goofy -> snowboardingImg.setImageResource(R.drawable.goofy_stalefish)
                Stance.Regular -> snowboardingImg.setImageResource(R.drawable.regular_stalefish)
            }
            return getFormatTrick(Stalefish, rotation)
        }
    },
    Method("method","Передней рукой\nМожно добавить не много магии") {
        override fun showTrick(): String {
            when (stance) {
                Stance.Goofy -> snowboardingImg.setImageResource(R.drawable.goofy_method)
                Stance.Regular -> snowboardingImg.setImageResource(R.drawable.regular_method)
            }
            return getFormatTrick(Method, rotation)
        }
    },
    Japan("japan","Передней рукой\nПереднее колено согнуть и направить вниз") {
        override fun showTrick(): String {
            when (stance) {
                Stance.Goofy -> snowboardingImg.setImageResource(R.drawable.goofy_japan)
                Stance.Regular -> snowboardingImg.setImageResource(R.drawable.regular_japan)
            }
            return getFormatTrick(Japan, rotation)
        }
    },
    Crail("crail","Задней рукой\nЕщё можно хватать за нос доски") {
        override fun showTrick(): String {
            when (stance) {
                Stance.Goofy -> snowboardingImg.setImageResource(R.drawable.goofy_crail)
                Stance.Regular -> snowboardingImg.setImageResource(R.drawable.regular_crail)
            }
            return getFormatTrick(Crail, rotation)
        }
    },
    RocketAir("rocket air","Двумя руками\nНичего сложного") {
        override fun showTrick(): String {
            snowboardingImg.setImageResource(R.drawable.rocket_air)
            return getFormatTrick(RocketAir, rotation)
        }
    },
    Stelmasky("stelmasky","Передней рукой\nРуку провести между ногами") {
        override fun showTrick(): String {
            when (stance) {
                Stance.Goofy -> snowboardingImg.setImageResource(R.drawable.goofy_stelmasky)
                Stance.Regular -> snowboardingImg.setImageResource(R.drawable.regular_stelmasky)
            }
            return getFormatTrick(Stelmasky, rotation)
        }
    },
    RoastBeef("roast beef","Задней рукой\nЧерез ноги проводим руку") {
        override fun showTrick(): String {
            when (stance) {
                Stance.Goofy -> snowboardingImg.setImageResource(R.drawable.goofy_roast_beef)
                Stance.Regular -> snowboardingImg.setImageResource(R.drawable.regular_roast_beef)
            }
            return getFormatTrick(RoastBeef, rotation)
        }
    },
    Suitcase("suitcase","Держим передней рукой\nСхватить нужно через базу" +
            "Трюк напоминает Method, но держать за другой кант") {
        override fun showTrick(): String {
            when (stance) {
                Stance.Goofy -> snowboardingImg.setImageResource(R.drawable.goofy_suitcase)
                Stance.Regular -> snowboardingImg.setImageResource(R.drawable.regular_suitcase)
            }
            return getFormatTrick(Suitcase, rotation)
        }
    },
    CrossRocket("cross rocket","Двумя руками\nРуки скрещены") {
        override fun showTrick(): String {
            snowboardingImg.setImageResource(R.drawable.cross_rocket)
            return getFormatTrick(CrossRocket, rotation)
        }
    },
    DoubleTail("double tail","Схватить обеими руками\nТянуться нужно через грудь") {
        override fun showTrick(): String {
            snowboardingImg.setImageResource(R.drawable.double_tail)
            return getFormatTrick(DoubleTail, rotation)
        }
    },
    ReachAround("reach around","Хватать передней рукой\nТянуться через спину") {
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