package com.example.retrofitpostgit

import com.google.gson.annotations.SerializedName

class myData () {

    var data: List<userInfo>?= null

    class userInfo {
        @SerializedName("pk")
        var pk : Int ?= null

        @SerializedName("name")
        var name : String? =null

        @SerializedName("location")
        var location : String?=null

        constructor(name:String, loc:String){
            this.name=name
            this.location=loc
        }
    }


}