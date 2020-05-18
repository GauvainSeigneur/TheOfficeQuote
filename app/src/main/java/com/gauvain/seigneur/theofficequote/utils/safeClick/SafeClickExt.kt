package com.gauvain.seigneur.theofficequote.utils.safeClick

import android.view.View

fun View.setOnSafeClickListener(interval: Int? = null, onSafeClick: (View) -> Unit) {
    setOnClickListener(
        SafeClickListener(
            interval
        ) { view ->
            onSafeClick(view)
        }
    )
}
