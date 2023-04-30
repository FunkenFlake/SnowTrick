package com.snowboard.android.snowtrick

import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorCompat

class RotationAnimation {

    fun getOneEightyClockWise(): ViewPropertyAnimatorCompat {
        return ViewCompat.animate(snowboardingImg)
            .rotation(180f)
            .setDuration(680)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .setStartDelay(50)
            .withEndAction {
                snowboardingImg.animate().rotation(0f).duration = 0
            }
    }

    fun getOneEightyAntiClockWise(): ViewPropertyAnimatorCompat {
        return ViewCompat.animate(snowboardingImg)
            .rotation(-180f)
            .setDuration(680)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .setStartDelay(50)
            .withEndAction {
                snowboardingImg.animate().rotation(0f).duration = 0
            }
    }

    fun getThreeSixtyClockWise(): ViewPropertyAnimatorCompat {
        return ViewCompat.animate(snowboardingImg)
            .rotation(360f)
            .setDuration(930)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .setStartDelay(50)
            .withEndAction {
                snowboardingImg.animate().rotation(0f).duration = 0
            }
    }

    fun getThreeSixtyAntiClockWise(): ViewPropertyAnimatorCompat {
        return ViewCompat.animate(snowboardingImg)
            .rotation(-360f)
            .setDuration(930)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .setStartDelay(50)
            .withEndAction {
                snowboardingImg.animate().rotation(0f).duration = 0
            }
    }

    fun getFade(showTrickView: TextView): ViewPropertyAnimatorCompat {
        return ViewCompat.animate(showTrickView)
            .alpha(0f)
            .setDuration(0)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .withEndAction {
                showTrickView.animate().alpha(1f).duration = 480
            }
    }
}