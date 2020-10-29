package com.example.eggmoney

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_search_result.*


class SearchResultActivity : AppCompatActivity() {
    private var sendMsg = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        stock_name.text = intent.getStringExtra("stock_name")

        activity_search_gift_button.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK);
            intent.setData(Uri.parse("content://com.android.contacts/data/phones"));
            startActivityForResult(intent, 10);


            // TODO: 전화번호부 가져오기
            // TODO: 서버에게 stock_name 혹은 종목코드로 선물 요청 보내기
            // TODO: 난수 코드 받기
            // TODO: 전화번호 받으면 그걸로 SMS로 난수코드 보내기
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



