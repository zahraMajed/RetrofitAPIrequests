package com.example.retrofitpostgit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdataDeleteUser : AppCompatActivity() {
    lateinit var btnUpdate: Button
    lateinit var btnDel: Button
    lateinit var edName: EditText
    lateinit var edLoc: EditText
    lateinit var edId: EditText

    lateinit var name:String
    lateinit var location:String
    var id:Int=0
    var apiInterface:ApiInterface?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_updata_delete_user)

        //step 3: declare and init my XML components
        btnUpdate=findViewById(R.id.btnUpd)
        btnDel=findViewById(R.id.btnDel)
        edName=findViewById(R.id.edName)
        edLoc=findViewById(R.id.edLoc)
        edId=findViewById(R.id.edId)

        //step 7: make connection to internet
        //we need to have interface in order to make connection to internet
        //to create the interface:
        //val interface variable name=api clint class name. api clint class method?create(api interface name::c;ass.java)
        //this apiInterface is object for our api interface that we created previously
        apiInterface=ApiClint().getClient()?.create(ApiInterface::class.java)

        btnUpdate.setOnClickListener(){
            getUserDetails()
            updataUser()
        }//end btnUpdate listerner

        btnDel.setOnClickListener(){
            getUserDetails()
            delUser()
        }//end btnDel listerner

    }//end onCreate()

    fun getUserDetails(){
        id=edId.text.toString().toInt()
        name= edName.text.toString()
        location=edLoc.text.toString()
    }//end getUserDetails()

    fun updataUser(){
        //step 8: check internet and make network call
        if(apiInterface != null) {
            apiInterface?.updateUser(id, myData.userInfo(id, name, location))?.enqueue(object : Callback<myData.userInfo?> {
                override fun onResponse(
                    call: Call<myData.userInfo?>,
                    response: Response<myData.userInfo?>
                ) {
                    Toast.makeText(this@UpdataDeleteUser,"User details updated successfully", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<myData.userInfo?>, t: Throwable) {
                    Toast.makeText(this@UpdataDeleteUser,"Something went wrong!", Toast.LENGTH_LONG).show()
                }
            })
        }
    }//end updataUser()

    fun delUser(){
        //step 8: check internet and make network call
        if(apiInterface != null) {
            apiInterface?.deleteUser(id)?.enqueue(object : Callback<Void?> {
                override fun onResponse(call: Call<Void?>, response: Response<Void?>) {
                    Toast.makeText(this@UpdataDeleteUser,"User deleted successfully", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<Void?>, t: Throwable) {
                    Toast.makeText(this@UpdataDeleteUser,"Something went wrong!", Toast.LENGTH_LONG).show()
                }
            })
        }

    }//end delUser()

}//end class