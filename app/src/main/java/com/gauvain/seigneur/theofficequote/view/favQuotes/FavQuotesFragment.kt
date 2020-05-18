package com.gauvain.seigneur.theofficequote.view.favQuotes

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gauvain.seigneur.theofficequote.R
import com.gauvain.seigneur.theofficequote.model.LiveDataState
import com.gauvain.seigneur.theofficequote.model.LoadingState
import com.gauvain.seigneur.theofficequote.model.QuoteDetailsData
import com.gauvain.seigneur.theofficequote.utils.event.EventObserver
import com.gauvain.seigneur.theofficequote.view.details.QuoteDetailsActivity
import com.gauvain.seigneur.theofficequote.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_fav_quotes.*
import kotlinx.android.synthetic.main.fragment_fav_quotes.loadingView
import org.koin.android.viewmodel.ext.android.viewModel

class FavQuotesFragment : BaseFragment(), FavQuoteListAdapter.Listener {

    override val fragmentLayout: Int
        get() = R.layout.fragment_fav_quotes
    private val viewModel: FavQuotesViewModel by viewModel()
    private lateinit var adapter: FavQuoteListAdapter

    override fun onClick(id: Int?) {
        id?.let {
            viewModel.getQuotesDetails(it)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (viewModel.quoteList?.value == null) {
            viewModel.getFavQuotes(checkNetworkState(context))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        viewModel.quoteList?.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        viewModel.displayDetailsEvent.observe(viewLifecycleOwner, EventObserver {
            when (it) {
                is LiveDataState.Success -> displayDetails(it.data)
                is LiveDataState.Error -> {
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG).show()
                }
            }
        })

        viewModel.initialLoadingState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is LiveDataState.Success -> {
                    when(it.data) {
                        LoadingState.IS_LOADING -> {
                            loadingView.visibility = View.VISIBLE
                            loadingView.setLoading()
                        }
                        else -> {
                           loadingView.visibility = View.GONE
                        }
                    }
                }
                is LiveDataState.Error -> {
                    loadingView.visibility = View.VISIBLE
                    loadingView.setError(
                        it.errorData.title?.getFormattedString(view.context),
                        it.errorData.description?.getFormattedString(view.context),
                        it.errorData.buttonText?.getFormattedString(view.context)
                    )
                    { viewModel.resetList() }
                }
            }
        }
        )
    }

    private fun displayDetails(data: QuoteDetailsData) {
        context?.let {
            startActivity(QuoteDetailsActivity.newIntent(it, data))
        }
    }

    private fun initAdapter() {
        adapter = FavQuoteListAdapter(this)
        favQuotesRecyclerview.layoutManager = LinearLayoutManager(
            context, RecyclerView.VERTICAL,
            false
        )
        favQuotesRecyclerview.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )
        favQuotesRecyclerview.adapter = adapter
    }

    private fun checkNetworkState(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val nw = connectivityManager.activeNetwork ?: return false
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