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
    lateinit var edId:EditText

    lateinit var name:String
    lateinit var location:String
    var id:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_user)

        //step 3: declare and init my XML components
        btnSave=findViewById(R.id.btnSave)
        btnView=findViewById(R.id.btnView)
        edName=findViewById(R.id.edName)
        edLoc=findViewById(R.id.edLoc)
        edId=findViewById(R.id.edId)

        btnSave.setOnClickListener(){
            id=edId.text.toString().toInt()
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
        //step 7: make connection to internet
        //we need to have interface in order to make connection to internet
        //to create the interface:
        //val interface variable name=api clint class name. api clint class method?create(api interface name::c;ass.java)
        //this apiInterface is object for our api interface that we created previously
        val apiInterface=ApiClint().getClient()?.create(ApiInterface::class.java)

        //step 8: check internet and make network call
        apiInterface?.addUser(myData.userInfo(id, name,location))?.enqueue(object : Callback<myData.userInfo?> {
            override fun onResponse(
                call: Call<myData.userInfo?>,
                response: Response<myData.userInfo?>
            ) {
                Toast.makeText(this@NewUser,"User added successfully",Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<myData.userInfo?>, t: Throwable) {
                Toast.makeText(this@NewUser,"Something went wrong!",Toast.LENGTH_LONG).show()
            }
        })
    }
}