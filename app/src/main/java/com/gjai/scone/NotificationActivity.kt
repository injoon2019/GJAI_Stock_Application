package com.gjai.scone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_notification.*


class NotificationActivity : AppCompatActivity() {
    private var news_list: Int = 0
    var notification_List = arrayListOf<NotificationItem>(
        NotificationItem(
            "쿠폰이 전해졌습니다",
            "구슬님께서 빌리님이 보내신 선물을 받았습니다.",
            "(감사인사 받기를 놓치지 마세요.)",
            "11월 05일 11:50"
        ),
        NotificationItem(
            "빅히트 (352820 코스피)가 등록되었습니다.",
            "6개월 후 매도 주문이 가능합니다.",
            "(메시지를 확인해 보세요.)",
            "11월 01일 17:50"
        ),
        NotificationItem(
            "효성 ITX(094280 코스피)가 등록되었습니다",
            "즉시 매도 주문이 가능합니다.",
            "(메시지를 확인해 보세요.)",
            "10월 28일 09:30"
        ),
        NotificationItem(
            "쿠폰이 전해졌습니다",
            "장범준님께서 빌리님이 보내신 선물을 받았습니다.",
            "(감사인사 받기를 놓치지 마세요.)",
            "10월 25일 08:25"
        ),
        NotificationItem(
            "쿠폰이 전해졌습니다",
            "판테온님께서 빌리님이 보내신 선물을 받았습니다.",
            "(감사인사 받기를 놓치지 마세요.)",
            "10월 21일 11:10"
        ),
        NotificationItem(
            "쿠폰이 전해졌습니다",
            "장규리님께서 빌리님이 보내신 선물을 받았습니다.",
            "(감사인사 받기를 놓치지 마세요.)",
            "10월 19일 19:30"
        ),
        NotificationItem(
            "쿠폰이 전해졌습니다",
            "이새롬님께서 빌리님이 보내신 선물을 받았습니다.",
            "(감사인사 받기를 놓치지 마세요.)",
            "10월 16일 10:32"
        ),
        NotificationItem(
            "쿠폰이 전해졌습니다",
            "백종원님께서 빌리님이 보내신 선물을 받았습니다.",
            "(감사인사 받기를 놓치지 마세요.)",
            "10월 14일 15:58"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        notification_back_button.setOnClickListener {
            startActivity(Intent(this, InfoActivity::class.java))
            finish()
        }

        var nC_adapter = NotifucationCustomAdapter(this, notification_List)
        present_notification_main_Recycle.adapter = nC_adapter

        val lm = LinearLayoutManager(this)
        present_notification_main_Recycle.layoutManager = lm
        present_notification_main_Recycle.setHasFixedSize(true)

        srl_notification.setOnRefreshListener {
            // 사용자가 아래로 드래그 했다가 놓았을 때 호출 됩니다.
            // 이때 새로고침 화살표가 계속 돌아갑니다.
            // 서버통신~~
            var notification_List_1 = arrayListOf<NotificationItem>(
                NotificationItem(
                    "쿠폰이 전해졌습니다",
                    "아이유님께서 조커님이 보내신 선물을 받았습니다.",
                    "(감사인사 받기를 놓치지 마세요.)",
                    "11월 05일 11:50"
                )
            )
            if (news_list == 0) {
                notification_List_1 = arrayListOf<NotificationItem>(
                    NotificationItem(
                        "쿠폰이 전해졌습니다",
                        "정인선님께서 빌리님이 보내신 선물을 받았습니다.",
                        "(감사인사 받기를 놓치지 마세요.)",
                        "11월 07일 16:05"
                    ),
                    NotificationItem(
                        "쿠폰이 전해졌습니다",
                        "아이유님께서 빌리님이 보내신 선물을 받았습니다.",
                        "(감사인사 받기를 놓치지 마세요.)",
                        "11월 05일 11:50"
                    ),
                    NotificationItem(
                        "빅히트 (352820 코스피)가 등록되었습니다.",
                        "6개월 후 매도 주문이 가능합니다.",
                        "(메시지를 확인해 보세요.)",
                        "11월 01일 17:50"
                    ),
                    NotificationItem(
                        "효성 ITX(094280 코스피)가 등록되었습니다",
                        "즉시 매도 주문이 가능합니다.",
                        "(메시지를 확인해 보세요.)",
                        "10월 28일 09:30"
                    ),
                    NotificationItem(
                        "쿠폰이 전해졌습니다",
                        "장범준님께서 빌리님이 보내신 선물을 받았습니다.",
                        "(감사인사 받기를 놓치지 마세요.)",
                        "10월 25일 08:25"
                    ),
                    NotificationItem(
                        "쿠폰이 전해졌습니다",
                        "판테온님께서 빌리님이 보내신 선물을 받았습니다.",
                        "(감사인사 받기를 놓치지 마세요.)",
                        "10월 21일 11:10"
                    ),
                    NotificationItem(
                        "쿠폰이 전해졌습니다",
                        "장규리님께서 빌리님이 보내신 선물을 받았습니다.",
                        "(감사인사 받기를 놓치지 마세요.)",
                        "10월 19일 19:30"
                    ),
                    NotificationItem(
                        "쿠폰이 전해졌습니다",
                        "이새롬님께서 빌리님이 보내신 선물을 받았습니다.",
                        "(감사인사 받기를 놓치지 마세요.)",
                        "10월 16일 10:32"
                    )
                )
                news_list = 1
            } else {
                notification_List_1 = arrayListOf<NotificationItem>(
                    NotificationItem(
                        "쿠폰이 전해졌습니다",
                        "조보아님께서 빌리님이 보내신 선물을 받았습니다.",
                        "(감사인사 받기를 놓치지 마세요.)",
                        "11월 10일 13:15"
                    ),
                    NotificationItem(
                        "쿠폰이 전해졌습니다",
                        "정인선님께서 빌리님이 보내신 선물을 받았습니다.",
                        "(감사인사 받기를 놓치지 마세요.)",
                        "11월 07일 16:05"
                    ),
                    NotificationItem(
                        "쿠폰이 전해졌습니다",
                        "아이유님께서 빌리님이 보내신 선물을 받았습니다.",
                        "(감사인사 받기를 놓치지 마세요.)",
                        "11월 05일 11:50"
                    ),
                    NotificationItem(
                        "빅히트 (352820 코스피)가 등록되었습니다.",
                        "6개월 후 매도 주문이 가능합니다.",
                        "(메시지를 확인해 보세요.)",
                        "11월 01일 17:50"
                    ),
                    NotificationItem(
                        "효성 ITX(094280 코스피)가 등록되었습니다",
                        "즉시 매도 주문이 가능합니다.",
                        "(메시지를 확인해 보세요.)",
                        "10월 28일 09:30"
                    ),
                    NotificationItem(
                        "쿠폰이 전해졌습니다",
                        "장범준님께서 빌리님이 보내신 선물을 받았습니다.",
                        "(감사인사 받기를 놓치지 마세요.)",
                        "10월 25일 08:25"
                    ),
                    NotificationItem(
                        "쿠폰이 전해졌습니다",
                        "판테온님께서 빌리님이 보내신 선물을 받았습니다.",
                        "(감사인사 받기를 놓치지 마세요.)",
                        "10월 21일 11:10"
                    ),
                    NotificationItem(
                        "쿠폰이 전해졌습니다",
                        "장규리님께서 빌리님이 보내신 선물을 받았습니다.",
                        "(감사인사 받기를 놓치지 마세요.)",
                        "10월 19일 19:30"
                    )
                )
                news_list = 0
            }


            var SW_adapter = NotifucationCustomAdapter(this, notification_List_1)
            present_notification_main_Recycle.adapter = SW_adapter
            srl_notification.isRefreshing = false //서버 통신 완료 후 호출해줍니다.
        }
    }

}
class NotificationItem(var name: String, var text_1: String,var text_2: String, var timestamp: String)
