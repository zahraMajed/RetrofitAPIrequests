package com.example.retrofitpostgit

import retrofit2.Call
import retrofit2.http.*

//step 6: Create APIinterface
//(APIinterface is just class name, it's not fix i can name it whatever i want
//this class helping me to do API call in main activity as i will make object of this interface
//this interface must created after model class as we will call model class here
//this code is always fix except endpoint URL

//in this interface you will list your API call that you need it
//API call: get,post,put and delete

interface ApiInterface {

    //get request: get -read- data from API
    @Headers("Content-Type: application/json")
    @GET("/test/")
    fun getDate (): Call<List<myData.userInfo>>?

    //post request: add -insert- data to API
    //we must use @Body
    @Headers("Content-Type: application/json")
    @POST("/test/")
    fun addUser (@Body info:myData.userInfo): Call<myData.userInfo>

    //put request: update of replace full specific object
    //we must use @Path to change individual fields
    @Headers("Content-Type: application/json")
    @PUT("/test/{id}")
    fun updateUser(@Path("id") id:Int, @Body info:myData.userInfo): Call<myData.userInfo>

    //delete request: delete full specific object
    //we must use @Path to change individual fields
    //this must return Call<Void> to overwrite an existing post
    @Headers("Content-Type: application/json")
    @DELETE("/test/{id}")
    fun deleteUser(@Path("id") id:Int): Call<Void>

}