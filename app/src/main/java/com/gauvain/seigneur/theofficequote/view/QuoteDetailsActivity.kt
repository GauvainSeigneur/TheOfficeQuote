package com.gauvain.seigneur.theofficequote.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gauvain.seigneur.theofficequote.R
import com.gauvain.seigneur.theofficequote.model.QuoteDetailsData
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.activity_quote_details.*

class QuoteDetailsActivity : AppCompatActivity() {

    companion object {

        private const val DATA_KEY = "data_key"

        fun newIntent(
            context: Context,
            data: QuoteDetailsData
        ): Intent =
            Intent(context, QuoteDetailsActivity::class.java)
                .putExtra(DATA_KEY, data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quote_details)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        val details: QuoteDetailsData? = intent.getParcelableExtra(DATA_KEY)
        displayData(details)

    }

    private fun displayData(data: QuoteDetailsData?) {
        data?.let { quoteData->
            quoteTextView.text = String.format(getString(R.string.quote_placeholder), quoteData.body )
            authorTextView.text = String.format(getString(R.string.author_placeholder), quoteData.author )
            displayTags(quoteData.tags)
            countView.setData(quoteData.favoritesCount, quoteData.upvotesCount, quoteData.downvotesCount)
            linkButton.setOnClickListener {
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(quoteData.url)
                startActivity(i)
            }
        }?: displayNoData()

    }

    private fun displayNoData() {
        finish()
        Toast.makeText(this, getString(R.string.common_error_info), Toast.LENGTH_LONG).show()
    }

    private fun displayTags(tagList:List<String>) {
        for (tag in tagList) {
            val chip = Chip(tagChipGroup.context)
            chip.text= tag
            // necessary to get single selection working
            chip.isClickable = false
            chip.isCheckable = false
            tagChipGroup.addView(chip)
        }
    }
}
