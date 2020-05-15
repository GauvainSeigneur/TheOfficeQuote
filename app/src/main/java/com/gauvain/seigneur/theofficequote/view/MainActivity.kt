package com.gauvain.seigneur.theofficequote.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gauvain.seigneur.theofficequote.R

class MainActivity : AppCompatActivity() {

    companion object {
        fun newIntent(
            context: Context
        ): Intent = Intent(context, MainActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}
