package com.gauvain.seigneur.theofficequote.utils

import android.graphics.drawable.AnimatedVectorDrawable
import android.widget.ImageView

fun ImageView.startVectorAnimation() {
    val avd = this.drawable as? AnimatedVectorDrawable
    avd?.start()
}

fun ImageView.stopVectorAnimation() {
    val avd = this.drawable as? AnimatedVectorDrawable
    avd?.stop()
}