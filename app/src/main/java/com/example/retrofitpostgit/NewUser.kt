package com.example.retrofitpostgit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewUser : AppCompatActivity() {
    lateinit var btnSave: Button
    lateinit var btnView: Button
    lateinit var edName: EditText
    lateinit var edLoc: EditText

    lateinit var name:String
    lateinit var location:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_user)

        btnSave=findViewById(R.id.btnSave)
        btnView=findViewById(R.id.btnView)
        edName=findViewById(R.id.edName)
        edLoc=findViewById(R.id.edLoc)

        btnSave.setOnClickListener(){
            name= edName.text.toString()
            location=edLoc.text.toString()
             addUser()

        }//end listerner

        btnView.setOnClickListener(){
            intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun addUser(){
        val apiInterface=ApiClint().getClient()?.create(ApiInterface::class.java)

        apiInterface?.addUser(myData.userInfo(name,location))?.enqueue(object : Callback<myData.userInfo?> {
            override fun onResponse(
                call: Call<myData.userInfo?>,
                response: Response<myData.userInfo?>
            ) {
                Toast.makeText(this@NewUser,"user added",Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<myData.userInfo?>, t: Throwable) {
                Toast.makeText(this@NewUser,"something went wrong!",Toast.LENGTH_LONG).show()
            }
        })
    }
}