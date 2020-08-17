package com.example.thiandroid.network

import UserResponses
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoint {

    @GET("api/users")
    fun getUser(@Query("page") page: Int): Call<UserResponses>

}