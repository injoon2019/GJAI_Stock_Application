package com.example.eggmoney
import com.example.eggmoney.CouponRegisterResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RegisterCoupon {
    @FormUrlEncoded
    @POST("/user/UseCoupon/")
    fun registerCoupon(
        @Field("CouponCode") couponCode: String?,
        @Field("uid") uid: String?
    ): Call<CouponRegisterResponse>
}