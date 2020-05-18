package com.gauvain.seigneur.theofficequote.view.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.gauvain.seigneur.theofficequote.R
import com.gauvain.seigneur.theofficequote.utils.FragmentStateManager
import com.gauvain.seigneur.theofficequote.view.favQuotes.FavQuotesFragment
import com.gauvain.seigneur.theofficequote.view.randomQuote.RandomQuoteFragment
import com.gauvain.seigneur.theofficequote.view.user.UserFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var fragmentStateManager: FragmentStateManager? = null

    companion object {
        fun newIntent(
            context: Context
        ): Intent = Intent(context, MainActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragmentManager(savedInstanceState)
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            showFragment(getNavPositionFromMenuItem(item))
            true
        }
        bottomNavigation.setOnNavigationItemReselectedListener {
            //do nothing
        }
    }

    /**
     * Initialize Fragment manager and default value
     */
    private fun initFragmentManager(savedInstanceState: Bundle?) {
        fragmentStateManager = object :
            FragmentStateManager(fragmentContainer, supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                when (position) {
                    0 -> return FavQuotesFragment()
                    1 -> return RandomQuoteFragment()
                    2 -> return UserFragment()
                }
                return FavQuotesFragment()
            }
        }
        if (savedInstanceState == null) {
            fragmentStateManager?.changeFragment(0)
        }
    }

    /**
     * get position form item id in bottom navigation menu
     */
    private fun getNavPositionFromMenuItem(menuItem: MenuItem): Int {
        return when (menuItem.itemId) {
            R.id.navigation_fav -> 0
            R.id.navigation_add -> 1
            R.id.navigation_user -> 2
            else -> 0
        }
    }

    /**
     * Show a fragment thanks to mFragmentStateManager
     */
    private fun showFragment(pos: Int) {
        fragmentStateManager?.changeFragment(pos)
    }
}
