package com.snowboard.android.snowtrick.animation

import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorCompat
import com.snowboard.android.snowtrick.Stance
import com.snowboard.android.snowtrick.snowboardingImg

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

fun checkAndGetAnimation (trick: CharSequence, stance: Stance) {

    val animation = RotationAnimation()

    if (stance == Stance.Goofy) {
        when {
            "FS" in trick && "180" in trick -> animation.getOneEightyClockWise()
            "BS" in trick && "180" in trick -> animation.getOneEightyAntiClockWise()
            "FS" in trick && "360" in trick -> animation.getThreeSixtyClockWise()
            "BS" in trick && "360" in trick -> animation.getThreeSixtyAntiClockWise()
        }
    } else {
        when {
            "BS" in trick && "180" in trick -> animation.getOneEightyClockWise()
            "FS" in trick && "180" in trick -> animation.getOneEightyAntiClockWise()
            "BS" in trick && "360" in trick -> animation.getThreeSixtyClockWise()
            "FS" in trick && "360" in trick -> animation.getThreeSixtyAntiClockWise()
        }
    }
}