package com.meli.utils.animations

import android.animation.Animator
import com.airbnb.lottie.LottieAnimationView

fun LottieAnimationView.onAnimationEnd(onFinish: () -> Unit) {
    this.addAnimatorListener(object : Animator.AnimatorListener {
        override fun onAnimationStart(animation: Animator) {
        }

        override fun onAnimationEnd(animation: Animator) {
            onFinish.invoke()
        }

        override fun onAnimationCancel(animation: Animator) {
        }

        override fun onAnimationRepeat(animation: Animator) {
        }
    })
}
