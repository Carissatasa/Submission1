package com.dicoding.mygithubuser.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.mygithubuser.data.response.GithubResponse
import com.dicoding.mygithubuser.data.response.ItemsItem
import com.dicoding.mygithubuser.data.retrofit.ApiConfig
import com.dicoding.mygithubuser.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)
        mainViewModel.listUser.observe(this) { listUser ->
            setUserData(listUser)
        }
        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        //searchBar
        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    searchView.hide()
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        val searchResult = searchView.text.toString() // Mengambil nilai query dari SearchView
                        if (!searchResult.isNullOrEmpty()) {
                            searchBar.text = searchView.text //untuk menampilkan pencarian di search bar
                            mainViewModel.findUser(searchResult) // Memanggil API dengan query yang sesuai
                        } else {
                            Toast.makeText(
                                this@MainActivity,
                                "Masukkan kata kunci pencarian",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        return@setOnEditorActionListener true
                    }
                    false
                }
        }

        val layoutManager = LinearLayoutManager(this)
        binding.rvUser.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvUser.addItemDecoration(itemDecoration)

        // findUser()
    }

    private fun setUserData(items: List<ItemsItem?>?) {
        adapter = UserAdapter()
        adapter.submitList(items)
        binding.rvUser.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }


}