package com.gauvain.seigneur.theofficequote.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.gauvain.seigneur.theofficequote.R
import com.gauvain.seigneur.theofficequote.utils.safeClick.setOnSafeClickListener
import com.gauvain.seigneur.theofficequote.utils.startVectorAnimation
import kotlinx.android.synthetic.main.view_loading.view.*
import kotlinx.android.synthetic.main.view_no_data_found.view.*

class LoadingView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {


    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.view_loading, this)
    }

    fun setLoading() {
        showError(false)
        showLoader(true)
    }

    fun setError(title: String?, desc: String?, buttonText: String?, retryListener: () -> Unit) {
        setErrorInfo(title, desc)
        showError(true)
        showLoader(false)
        retryButton.text = buttonText ?: context.getString(R.string.retry)
        retryButton.setOnSafeClickListener { retryListener() }
    }

    private fun showLoader(isVisible: Boolean) {
        if (isVisible) {
            bigLoader.visibility = View.VISIBLE
        } else {
            bigLoader.visibility = View.GONE
        }
    }

    private fun showError(isVisible: Boolean) {
        if (isVisible) {
            errorView.visibility = View.VISIBLE
            binocularAvdView.startVectorAnimation()
        } else {
            errorView.visibility = View.GONE
        }
    }

    private fun setErrorInfo(title: String?, desc: String?) {
        errorTitle.text = title
        errorDesc.text = desc
    }
}