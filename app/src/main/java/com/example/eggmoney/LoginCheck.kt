package com.example.eggmoney

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginCheck {
    @FormUrlEncoded
    @POST("/user/UserCheck/")
    fun checkLogin(
        @Field("uid") useruid:String,
        @Field("uname") username:String
    ): Call<Login>
}
