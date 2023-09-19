package com.dicoding.mygithubuser.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.mygithubuser.data.response.ItemsItem
import com.dicoding.mygithubuser.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersViewModel : ViewModel() {
    private val _listFollowers = MutableLiveData<List<ItemsItem>>()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object{
        internal const val TAG = "FollowersViewModel"
    }

    fun setListFollowers(username: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFollowers(username)
        Log.d(TAG, "Fetching followers for username: $username")
        client.enqueue(object : Callback<List<ItemsItem>> {
            override fun onResponse(
                call: Call<List<ItemsItem>>,
                response: Response<List<ItemsItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listFollowers.postValue(response.body())
//                    val followersList = response.body()
//                    Log.d(TAG, "Followers fetched successfully: $followersList")
//                    _listFollowers.postValue(followersList)

                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }


        })
    }

    fun getListFollowers(): LiveData<List<ItemsItem>>{
        return _listFollowers
    }

}