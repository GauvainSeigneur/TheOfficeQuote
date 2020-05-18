package com.gauvain.seigneur.theofficequote.view.details

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.gauvain.seigneur.theofficequote.R
import kotlinx.android.synthetic.main.view_details_count.view.*

class DetailsCountView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.view_details_count, this)
        orientation = HORIZONTAL
    }

    fun setData(favCount:String, upVote:String, downVote:String) {
        favCountTextView.text =  favCount
        upVoteCountTextView.text = upVote
        downVoteCountTextView.text = downVote
    }

}