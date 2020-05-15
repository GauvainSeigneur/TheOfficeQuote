package com.gauvain.seigneur.theofficequote.view.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.gauvain.seigneur.theofficequote.model.LiveDataState
import com.gauvain.seigneur.theofficequote.view.MainActivity
import com.gauvain.seigneur.theofficequote.view.login.LoginActivity
import org.koin.android.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.tokenData.observe(this, Observer {
            when(it) {
                is LiveDataState.Success -> {
                    startActivity(MainActivity.newIntent(this@SplashActivity))
                }
                is LiveDataState.Error -> {
                    startActivity(LoginActivity.newIntent(this@SplashActivity))
                }
            }
        })
    }
}
