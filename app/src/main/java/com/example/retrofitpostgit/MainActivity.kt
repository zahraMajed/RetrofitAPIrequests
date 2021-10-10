package com.example.retrofitpostgit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var btnAdd:Button
    lateinit var tvUser:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdd=findViewById(R.id.btnAdd)
        tvUser=findViewById(R.id.tvUser)

        btnAdd.setOnClickListener(){
            intent= Intent(this,NewUser::class.java)
            startActivity(intent)
        }
        getAPIResult()
    }//end onCreate()

    fun getAPIResult(){
        val apiInterface=ApiClint().getClient()?.create(ApiInterface::class.java)

        apiInterface?.getDate()?.enqueue(object : Callback<List<myData.userInfo>?> {
            override fun onResponse(call: Call<List<myData.userInfo>?>, response: Response<List<myData.userInfo>?>
            ) {
                val response=response.body()

                var displayResponse=""

                for (dataItem in response!!){
                    displayResponse += "${dataItem.name} \n${dataItem.location} \n \n \n"
                }
                tvUser.text=displayResponse
            }

            override fun onFailure(call: Call<List<myData.userInfo>?>, t: Throwable) {
                call.cancel()
            }
        })

    }//end getAPIResult()

    /*

     */
}

private fun <T> Call<T>?.enqueue(callback: Callback<myData?>) {

}
