package com.example.retrofitpostgit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var btnAdd:Button
    lateinit var tvUser:TextView
    lateinit var btnUpDel:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //step 3: declare and init my XML components
        btnAdd=findViewById(R.id.btnAdd)
        tvUser=findViewById(R.id.tvUser)
        btnUpDel=findViewById(R.id.btnUpDel)
        getAPIResult()
        btnAdd.setOnClickListener(){
            intent= Intent(this,NewUser::class.java)
            startActivity(intent)
        }
        btnUpDel.setOnClickListener(){
            intent= Intent(this,UpdataDeleteUser::class.java)
            startActivity(intent)
        }

    }//end onCreate()

    override fun onStart() {
        super.onStart()
        Toast.makeText(applicationContext,"onStart()",Toast.LENGTH_LONG).show()
        getAPIResult()
    }

    fun getAPIResult(){
        //step 7: make connection to internet
        //we need to have interface in order to make connection to internet
        //to create the interface:
        //val interface variable name=api clint class name. api clint class method?create(api interface name::c;ass.java)
        //this apiInterface is object for our api interface that we created previously
        val apiInterface=ApiClint().getClient()?.create(ApiInterface::class.java)

        //step 8: check internet and make network call
        //apiInterface.type your get method name that you declare it in the interface.enqueue(shift+ctrl+space)
        //enqueue method will send request to access
        /* make IDE complete the code by clicking shift+ctrl+space inside parentheses pf enqueue
        apiInterface?.getDate()?.enqueue(shift+ctrl+space)
         */
        if (apiInterface != null) {
            apiInterface?.getDate()?.enqueue(object : Callback<List<myData.userInfo>?> {
                override fun onResponse(
                    call: Call<List<myData.userInfo>?>, response: Response<List<myData.userInfo>?>
                ) {
                    //get access to data list
                    val response = response.body()
                    var displayResponse = ""
                    for (dataItem in response!!) {
                        displayResponse += "User ID: ${dataItem.pk} \nUser Name: ${dataItem.name} " +
                                "\nUser Location: ${dataItem.location} \n \n \n"
                    }
                    tvUser.text = displayResponse
                }
                override fun onFailure(call: Call<List<myData.userInfo>?>, t: Throwable) {
                    call.cancel()
                }
            })
        }
    }//end getAPIResult()


}
