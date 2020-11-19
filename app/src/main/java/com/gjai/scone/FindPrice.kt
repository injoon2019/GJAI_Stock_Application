import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface FindPrice {
    @FormUrlEncoded
    @POST("/stock/price")
    fun findStockPrice(
        @Field("StockCode") stockcode: String?
    ): Call<StockPrice>
}

data class StockPrice (
    val price: String
)
