package com.dicoding.mygithubuser.ui

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.mygithubuser.R
import com.dicoding.mygithubuser.databinding.FragmentFollowBinding

class FollowersFragment : Fragment(R.layout.fragment_follow) {
    private var binding: FragmentFollowBinding? = null
    private lateinit var viewModel: FollowersViewModel
    private lateinit var adapter: UserAdapter
    private lateinit var username : String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments
        username = args?.getString(DetailUser.KEY_NAME).toString()
        binding = FragmentFollowBinding.bind(view)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        binding?.apply {
            rvUser.setHasFixedSize(true)
            rvUser.layoutManager = LinearLayoutManager(activity)
            rvUser.adapter = adapter
        }

        showLoading(true)
        viewModel = ViewModelProvider(requireActivity()).get(FollowersViewModel::class.java)
        viewModel.setListFollowers(username)

        Log.e(FollowersViewModel.TAG, "Fetching followers for username: $username")

        viewModel.getListFollowers().observe(viewLifecycleOwner, Observer {
            if (it!=null){
                adapter.submitList(it)
                showLoading(false)
            }
        })

        //nambah ini
        val layoutManager = LinearLayoutManager(requireActivity())
        binding!!.rvUser.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireActivity(), layoutManager.orientation)
        binding!!.rvUser.addItemDecoration(itemDecoration)
    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }

    private fun showLoading(isLoading: Boolean) {
        binding!!.progressBar.visibility = if (isLoading) View.VISIBLE
        else View.GONE
    }



}