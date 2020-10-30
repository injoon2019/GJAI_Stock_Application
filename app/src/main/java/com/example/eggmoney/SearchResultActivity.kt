package com.example.eggmoney

import BuyCoupon
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

import kotlinx.android.synthetic.main.activity_search_result.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SearchResultActivity : AppCompatActivity() {
    private var sendMsg = "안녕하세요 ~ 보겠습    "
    private val BaseURL:String = "https://scone-294002.uc.r.appspot.com"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        stock_name.text = intent.getStringExtra("stock_name")
        var stockCode = intent.getStringExtra("stock_code")
        val uid = intent.getStringExtra("uid")

        activity_search_gift_button.setOnClickListener {
            // TODO: 서버에게 stock_name 혹은 종목코드로 선물 요청 보내기
            // TODO: 전화번호부 가져오
            // TODO: 난수 코드 받기
            // TODO: 전화번호 받으면 그걸로 SMS로 난수코드 보내기

            val retrofit = Retrofit.Builder().baseUrl(BaseURL) .addConverterFactory(
                GsonConverterFactory.create()).build();
            var buycoupon = retrofit.create(BuyCoupon::class.java);

            buycoupon.buyCoupon(uid, stockCode, 12345).enqueue(object:
                Callback<Coupon> {
                var buycoupon_response:Coupon? = null
                override fun onFailure(call: Call<Coupon>, t: Throwable) {
                    t.message?.let { Log.e("LOGIN", it) }
                    var dialog = AlertDialog.Builder(this@SearchResultActivity)
                    dialog.setTitle("에러")
                    dialog.setMessage("호출 실패")
                    dialog.show()
                }

                override fun onResponse(call: Call<Coupon>, response: Response<Coupon>) {
                    buycoupon_response = response.body()
                    Log.d("LOGIN", "msg : "+buycoupon_response?.ResultCode)
//                Log.d("LOGIN","code : "+login?.code)
//                    var dialog = AlertDialog.Builder(this@SearchResultActivity)
//                    dialog.setTitle(buycoupon_response?.CouponCode.toString())
                    var coupon_code:String = buycoupon_response?.CouponCode.toString()
//                    Toast.makeText(this, coupon_code+" 쿠폰 발급", Toast.LENGTH_SHORT).show()
//                dialog.setMessage(login?.code)
//                    dialog.show()
                }

            })



            //전화번호부 가져오기
            val intent = Intent(Intent.ACTION_PICK);
            intent.setData(Uri.parse("content://com.android.contacts/data/phones"));
            startActivityForResult(intent, 10);


        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            sendSMS(data!!, sendMsg)
        }

        super.onActivityResult(requestCode, resultCode, data)

    }

    @SuppressLint("SetTextI18n")
    private fun sendSMS(data: Intent, msg: String) {

        with(contentResolver) {
            data.data?.let {
                query(
                    it, arrayOf(
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                    ), null, null, null
                )
            }
        }?.apply {
            moveToFirst()
            val name = getString(0)     //0은 이름을 얻어옵니다.
            val number = getString(1)   //1은 번호를 받아옵니다.

            close()

            (findViewById<View>(R.id.activity_search_gift_button) as TextView).text = "name : $name number : $number"

            val n = Uri.parse("smsto: $number")
            val intent = Intent(Intent.ACTION_SENDTO, n)
            intent.putExtra("sms_body", msg)
            startActivity(intent)
        }
    }
}



