package com.example.retrofitpostgit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//step 5: create APIclient class (constant file)
//(APIclient is just class name, it's not fix i can name it whatever i want
//this class helping me to do API call in main activity and to create API interfse object in main as well
//this code is always fix except (URL)

//this is URL: https://dojo-recipes.herokuapp.com/test/
//you have to take URL without the endpoint, this is the endpoint: /test/ , it will ne in APIinterface
//and make sure to end the URL with /
class ApiClint {

    var retrofitBuilder: Retrofit?= null

    //getClient() is just a method name you can rename it
    fun getClient(): Retrofit? {
        val interceptor= HttpLoggingInterceptor()
        interceptor.level=HttpLoggingInterceptor.Level.BODY
        val client= OkHttpClient.Builder().addInterceptor(interceptor).build()
        //build retrofit
        retrofitBuilder = Retrofit.Builder()
            //do not use full url (full but except last part -endpoint-)
            .baseUrl("https://dojo-recipes.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofitBuilder
    }
}