package com.gauvain.seigneur.theofficequote.view.favQuotes

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gauvain.seigneur.theofficequote.R
import com.gauvain.seigneur.theofficequote.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_fav_quotes.*
import org.koin.android.viewmodel.ext.android.viewModel

class FavQuotesFragment : BaseFragment() {

    override val fragmentLayout: Int
        get() = R.layout.fragment_fav_quotes

    private val viewModel : FavQuotesViewModel by viewModel()

    private lateinit var adapter: FavQuoteListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        viewModel.quoteList.observe(viewLifecycleOwner, Observer {
            Log.d("pagingObserved", "$it")
            adapter.submitList(it)
        })
    }

    private fun initAdapter() {
        adapter = FavQuoteListAdapter()
        favQuotesRecyclerview.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,
            false)
        favQuotesRecyclerview.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }




}