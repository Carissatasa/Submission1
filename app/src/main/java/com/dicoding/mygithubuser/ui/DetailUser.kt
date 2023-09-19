package com.dicoding.mygithubuser.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.dicoding.mygithubuser.R
import com.dicoding.mygithubuser.data.response.DetailUserResponse
import com.dicoding.mygithubuser.databinding.ActivityDetailUserBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailUser : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var detailViewModel: DetailViewModel

    companion object {
        const val KEY_NAME = "key_name"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_followers,
            R.string.tab_following
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataUser = intent.getStringExtra(KEY_NAME)

        val bundle = Bundle()
        bundle.putString(KEY_NAME, dataUser)

        detailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailViewModel::class.java)
        detailViewModel.isLoading.observe(this) {
            showLoading(it)
        }
        detailViewModel.detailUsername(dataUser)
        detailViewModel.userName.observe(this) { userName ->
            setUsernameData(userName)
        }
//        if (!dataUser.isNullOrEmpty()) {
//
//        }



//        val user = if (Build.VERSION.SDK_INT >= 33) {
//            intent.getParcelableExtra<ItemsItem>(KEY_NAME, Country::class.java)
//        } else {
//            @Suppress("DEPRECATION")
//            intent.getParcelableExtra<ItemsItem>(KEY_NAME)
//        }

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager, bundle)
//        sectionsPagerAdapter.bundle = userName.toString()
        binding.apply{
            val viewPager: ViewPager2 = findViewById(R.id.view_pager)
            viewPager.adapter = sectionsPagerAdapter
            val tabs: TabLayout = findViewById(R.id.tabs)
            TabLayoutMediator(tabs, viewPager) { tab, position ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()
        }

//        val sectionsPagerAdapter = SectionsPagerAdapter(this, bundle)
//        sectionsPagerAdapter.username = binding.username.text =

//        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
//        viewPager.adapter = sectionsPagerAdapter

//        val tabs: TabLayout = findViewById(R.id.tabs)
//        TabLayoutMediator(tabs, viewPager) { tab, position ->
//            tab.text = resources.getString(TAB_TITLES[position])
//        }.attach()

        supportActionBar?.elevation = 0f
    }

//    private fun setUserData(items: <ItemsItem?>) {
//        adapter = UserAdapter()
//        adapter.submitList(items)
//        binding.rvUser.adapter = adapter
//    }

    private fun setUsernameData(user: DetailUserResponse) {
        binding.tvItem.text= user.login
        binding.tvName.text = user.name
        binding.tvNumbFollowing.text = user.followers.toString()
        binding.tvNumbFollowers.text = user.following.toString()
        Glide.with(this@DetailUser)
            .load(user.avatarUrl)
            .into(binding.imgPhoto)
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

}