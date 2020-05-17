package com.gauvain.seigneur.theofficequote.view.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.gauvain.seigneur.theofficequote.R
import com.gauvain.seigneur.theofficequote.model.LiveDataState
import com.gauvain.seigneur.theofficequote.utils.event.EventObserver
import com.gauvain.seigneur.theofficequote.view.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    companion object {
        fun newIntent(
            context: Context
        ): Intent = Intent(context, LoginActivity::class.java)
    }


    private val viewModel: LogInViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton.setOnClickListener {
            viewModel.login(userIdEditText.text.toString(), userPasswordEditText.text.toString())
        }
        observeLiveData()

    }

    private fun observeLiveData() {
        viewModel.loginEvent.observe(this, EventObserver{
            when(it) {
                is LiveDataState.Success -> startActivity(MainActivity.newIntent(this))
                is LiveDataState.Error -> { Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()}
            }
        })
    }
}
