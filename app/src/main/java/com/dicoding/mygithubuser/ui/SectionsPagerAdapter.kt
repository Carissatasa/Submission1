package com.dicoding.mygithubuser.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter


class SectionsPagerAdapter(
    fragmentActivity: FragmentActivity,
    supportFragmentManager: FragmentManager,
    bundle: Bundle
) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FollowersFragment()
            1 -> fragment = FollowingFragment()
        }
        return fragment as Fragment
    }
}

//    @StringRes
//    private val TAB_TITLES = intArrayOf(
//        R.string.tab_followers,
//        R.string.tab_following
//    )







//    private var fragmentBundle : Bundle
//
//    init {
//        fragmentBundle = data
//    }
//    var username: String = ""
//
//    override fun createFragment(position: Int): Fragment {
//        val fragment = FollowersFragment()
//        fragment.arguments = Bundle().apply {
//            putInt(FollowersFragment.ARG_POSITION, position + 1)
//            putString(FollowersFragment.ARG_USERNAME, username)
//        }
//        fragment?.arguments = this.fragmentBundle
//        return fragment
//    }
//    override fun getItemCount(): Int {
//        return 2
//    }
