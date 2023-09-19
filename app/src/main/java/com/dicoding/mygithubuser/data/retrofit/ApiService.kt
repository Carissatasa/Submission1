package com.dicoding.mygithubuser.data.retrofit

import com.dicoding.mygithubuser.data.response.DetailUserResponse
import com.dicoding.mygithubuser.data.response.GithubResponse
import com.dicoding.mygithubuser.data.response.ItemsItem
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/users") // Specify the base URL for the endpoint
    fun getUser(
        @Query("q") query: String
    ) : Call<GithubResponse>

    @GET("users/{username}")
    fun getDetailUser(@Path("username") username: String?): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    fun getFollowers(@Path("username") username: String): Call<List<ItemsItem>>
    @GET("users/{username}/following")
    fun getFollowing(@Path("username") username: String): Call<List<ItemsItem>>

}