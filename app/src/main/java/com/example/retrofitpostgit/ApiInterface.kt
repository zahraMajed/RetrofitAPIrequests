package com.example.retrofitpostgit

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {
    @GET("/test/")
    fun getDate (): Call<List<myData.userInfo>>?

    @POST("/test/")
    fun addUser (@Body info:myData.userInfo): Call<myData.userInfo>

}