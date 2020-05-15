package com.gauvain.seigneur.theofficequote.view.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gauvain.seigneur.theofficequote.R
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val viewModel: LogInViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton.setOnClickListener {
        }
    }
}
