package com.example.eggmoney

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.content_coupon_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CouponRegisterActivity : AppCompatActivity() {

    private lateinit var couponCode:String
    private val BaseURL:String = "https://scone-294002.uc.r.appspot.com"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coupon_register)
        setSupportActionBar(findViewById(R.id.coupon_toolbar))
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        val uid = intent.getStringExtra("uid")
        Toast.makeText(this, uid+"젠장?", Toast.LENGTH_SHORT).show()
//        val toolbar = findViewById(R.id.coupon_toolbar) as Toolbar

        coupon_register_button.setOnClickListener{
            couponCode = editTextCouponNumber.text.toString()
//            Toast.makeText(this, couponCode, Toast.LENGTH_SHORT).show()
//            Toast.makeText(this, uid, Toast.LENGTH_SHORT).show()

            val retrofit = Retrofit.Builder().baseUrl(BaseURL).addConverterFactory(
                GsonConverterFactory.create()).build();
            var registercoupon = retrofit.create(RegisterCoupon::class.java);


            var statusCode: String = "0"
            registercoupon.registerCoupon(couponCode, uid).enqueue(object:
                Callback<CouponRegisterResponse> {

                var registercoupon_response:CouponRegisterResponse? = null

                override fun onFailure(call: Call<CouponRegisterResponse>, t: Throwable) {
                    statusCode = "500"
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
                    statusCode = registercoupon_response?.ResultCode.toString()
                    var dialog = AlertDialog.Builder(this@CouponRegisterActivity)
//                    dialog.setTitle(registercoupon_response?.ResultMessage.toString())
                    dialog.setTitle(registercoupon_response?.ResultCode.toString())
//                    dialog.setTitle("테스트용")
//                    Toast.makeText(this, coupon_code+" 쿠폰 발급", Toast.LENGTH_SHORT).show()
//                dialog.setMessage(login?.code)
                    dialog.show()
                }

            })
            when(statusCode){
                "0" -> Toast.makeText(this, "널값", Toast.LENGTH_SHORT).show()
                "200" -> Toast.makeText(this, "쿠폰등록이 완료되었습니다", Toast.LENGTH_SHORT).show()
                "500" -> Toast.makeText(this, "쿠폰등록에 실패하였습니다", Toast.LENGTH_SHORT).show()
                else -> Toast.makeText(this, "알 수 없는 에러가 발생하였습니다", Toast.LENGTH_SHORT).show()
            }

        }



        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
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