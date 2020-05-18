package com.gauvain.seigneur.theofficequote.view.randomQuote

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.gauvain.seigneur.theofficequote.R
import com.gauvain.seigneur.theofficequote.model.LiveDataState
import com.gauvain.seigneur.theofficequote.model.LoadingState
import com.gauvain.seigneur.theofficequote.utils.safeClick.setOnSafeClickListener
import com.gauvain.seigneur.theofficequote.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_random_quote.*
import org.koin.android.viewmodel.ext.android.viewModel

class RandomQuoteFragment : BaseFragment() {

    private val viewModel : RandomQuoteViewModel by viewModel()

    override val fragmentLayout: Int
        get() = R.layout.fragment_random_quote

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        randomButtom.setOnSafeClickListener {
            viewModel.getRandom()
        }
        viewModel.loadingData.observe(viewLifecycleOwner, Observer {
            when(it) {
                LoadingState.IS_LOADING -> {
                    loadingView.visibility = View.VISIBLE
                    loadingView.setLoading()
                }
                LoadingState.IS_LOADED -> {
                    loadingView.visibility = View.GONE
                }
            }
        })

        viewModel.randomQuoteInfo.observe(viewLifecycleOwner, Observer {
            when(it) {
                is LiveDataState.Success -> {
                    randomQuoteLayout.visibility = View.VISIBLE
                    quoteTextView.text = it.data.body.getFormattedString(view.context)
                    authorTextView.text = it.data.author.getFormattedString(view.context)
                }
                is LiveDataState.Error -> {
                    loadingView.visibility = View.VISIBLE
                    randomQuoteLayout.visibility = View.GONE
                    loadingView.setError(
                        it.errorData.title?.getFormattedString(view.context),
                        it.errorData.description?.getFormattedString(view.context),
                        it.errorData.buttonText?.getFormattedString(view.context)
                    )
                    { viewModel.getRandom() }
                }
            }
        })
    }

}