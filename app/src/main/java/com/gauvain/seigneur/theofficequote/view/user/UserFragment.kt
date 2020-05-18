package com.gauvain.seigneur.theofficequote.view.user

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import coil.api.load
import com.gauvain.seigneur.theofficequote.R
import com.gauvain.seigneur.theofficequote.model.LiveDataState
import com.gauvain.seigneur.theofficequote.model.LoadingState
import com.gauvain.seigneur.theofficequote.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_user.*
import kotlinx.android.synthetic.main.fragment_user.loadingView
import org.koin.android.viewmodel.ext.android.viewModel

class UserFragment : BaseFragment() {

    private val viewModel : UserViewModel by viewModel()

    override val fragmentLayout: Int
        get() = R.layout.fragment_user


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

        viewModel.userInfo.observe(viewLifecycleOwner, Observer {
            when(it) {
                is LiveDataState.Success -> {
                    userPicView.load(it.data.picUrl
                    ) {
                        placeholder(R.drawable.ic_account_circle_white_24dp)
                        error(R.drawable.ic_account_circle_white_24dp)
                        fallback(R.drawable.ic_account_circle_white_24dp)
                    }
                    userLoginView.text = it.data.nickName
                    userFavoritessView.text = it.data.favCount.getFormattedString(view.context)
                }
                is LiveDataState.Error -> {
                    loadingView.visibility = View.VISIBLE
                    loadingView.setError(
                        it.errorData.title?.getFormattedString(view.context),
                        it.errorData.description?.getFormattedString(view.context),
                        it.errorData.buttonText?.getFormattedString(view.context)
                    )
                    { viewModel.getUser() }
                }
            }
        })



    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }


}