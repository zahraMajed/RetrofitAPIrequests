package com.example.retrofitpostgit

import com.google.gson.annotations.SerializedName
//step 4: create model class
//this hold JSON attribute
class myData () {

    var data: List<userInfo>?= null

    class userInfo {
        @SerializedName("pk")
        var pk : Int ?= null

        @SerializedName("name")
        var name : String? =null

        @SerializedName("location")
        var location : String?=null

        constructor(pk:Int,name:String, loc:String){
            this.pk=pk
            this.name=name
            this.location=loc
        }
    }


}