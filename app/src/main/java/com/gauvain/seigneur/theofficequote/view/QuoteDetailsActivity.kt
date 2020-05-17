package com.gauvain.seigneur.theofficequote.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.gauvain.seigneur.theofficequote.R
import com.gauvain.seigneur.theofficequote.model.QuoteDetailsData

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
        val details: QuoteDetailsData? = intent.getParcelableExtra(DATA_KEY)
        Toast.makeText(this, "data ${details?.id}", Toast.LENGTH_LONG).show()
    }
}
