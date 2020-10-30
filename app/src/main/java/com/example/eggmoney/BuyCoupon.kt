import com.example.eggmoney.Coupon
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface BuyCoupon {
    @FormUrlEncoded
    @POST("/user/BuyCoupon/")
    fun buyCoupon(
        @Field("uid") useruid: String?,
        @Field("StockCode") stockcode: String?,
        @Field("BuyPrice") buyprice:Int
    ): Call<Coupon>
}
