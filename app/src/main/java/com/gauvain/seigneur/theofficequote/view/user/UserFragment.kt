package com.gauvain.seigneur.theofficequote.view.user

import android.os.Bundle
import android.view.View
import com.gauvain.seigneur.theofficequote.R
import com.gauvain.seigneur.theofficequote.view.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class UserFragment : BaseFragment() {

    private val viewModel : UserViewModel by viewModel()

    override val fragmentLayout: Int
        get() = R.layout.fragment_user

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUser()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }




}