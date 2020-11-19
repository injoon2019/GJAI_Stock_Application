package com.gjai.scone

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_coupon_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CouponRegisterActivity : AppCompatActivity() {

    private lateinit var couponCode: String
    private val BaseURL: String = "https://scone-294502.du.r.appspot.com"
    private var statusMessage: String = "에러가 발생하였습니다"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coupon_register)
//        setSupportActionBar(findViewById(R.id.coupon_toolbar))
//        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        val uid = intent.getStringExtra("uid")
//        val toolbar = findViewById(R.id.coupon_toolbar) as Toolbar

        coupon_register_button.setOnClickListener {
            couponCode = editTextCouponNumber.text.toString()

            if (couponCode.length == 15) {
                val uid = intent.getStringExtra("uid")
//            Toast.makeText(this, couponCode, Toast.LENGTH_SHORT).show()
//            Toast.makeText(this, uid, Toast.LENGTH_SHORT).show()


                val retrofit = Retrofit.Builder().baseUrl(BaseURL).addConverterFactory(
                    GsonConverterFactory.create()
                ).build();
                var registercoupon = retrofit.create(RegisterCoupon::class.java);

                registercoupon.registerCoupon(couponCode, uid).enqueue(object :
                    Callback<CouponRegisterResponse> {

                    var registercoupon_response: CouponRegisterResponse? = null

                    override fun onFailure(call: Call<CouponRegisterResponse>, t: Throwable) {
                        t.message?.let { Log.e("LOGIN", it) }
                        var dialog = AlertDialog.Builder(this@CouponRegisterActivity)
                        dialog.setTitle("에러")
                        dialog.setMessage("호출 실패")
                        dialog.show()
                    }

                    override fun onResponse(
                        call: Call<CouponRegisterResponse>,
                        response: Response<CouponRegisterResponse>
                    ) {
                        registercoupon_response = response.body()
                        statusMessage = registercoupon_response?.ResultMessage.toString()
                        var dialog = AlertDialog.Builder(this@CouponRegisterActivity)
//                    dialog.setTitle(registercoupon_response?.ResultMessage.toString())
                        dialog.setTitle("쿠폰 등록 결과")
                        dialog.setMessage(statusMessage)
                        // 버튼 클릭시에 무슨 작업을 할 것인가!

                        var listener = object : DialogInterface.OnClickListener {
                            override fun onClick(p0: DialogInterface?, p1: Int) {
                                when (p1) {
                                }
                            }
                        }

                        dialog.setPositiveButton("확인", listener)
                        dialog.show()
//            Toast.makeText(this, couponCode, Toast.LENGTH_SHORT).show()
//            Toast.makeText(this, uid, Toast.LENGTH_SHORT).show()
                    }
                })
            }else{
                Toast.makeText(this, "쿠폰 번호를 확인해주세요", Toast.LENGTH_SHORT).show()
            }

        }

        coupon_register_back_button.setOnClickListener {
            startActivity(Intent(this, InfoActivity::class.java))
        }



    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {  // 뒤로가기 홈 버튼
        val id = item.itemId
        when (id) {
            android.R.id.home -> {
//                Toast.makeText(this, "쿠폰 액티비티 테스트", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, InfoActivity::class.java))
                //onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
data class CouponRegisterResponse (val ResultCode: String, val ResultMessage: String)