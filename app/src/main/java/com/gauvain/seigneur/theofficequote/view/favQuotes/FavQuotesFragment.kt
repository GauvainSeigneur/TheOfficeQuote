package com.gauvain.seigneur.theofficequote.view.favQuotes

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(viewModel.quoteList?.value == null) {
            viewModel.getFavQuotes(checkNetworkState(context))
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        viewModel.quoteList?.observe(viewLifecycleOwner, Observer {
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

    private fun checkNetworkState(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val nw      = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            val nwInfo = connectivityManager.activeNetworkInfo ?: return false
            return nwInfo.isConnected
        }
    }




}