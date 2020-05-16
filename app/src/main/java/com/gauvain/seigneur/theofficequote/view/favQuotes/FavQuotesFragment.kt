package com.gauvain.seigneur.theofficequote.view.favQuotes

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.gauvain.seigneur.theofficequote.R
import com.gauvain.seigneur.theofficequote.view.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class FavQuotesFragment : BaseFragment() {

    override val fragmentLayout: Int
        get() = R.layout.fragment_fav_quotes

    private val viewModel : FavQuotesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.quoteList.observe(viewLifecycleOwner, Observer {
            Log.d("pagingObserved", "$it")
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }




}