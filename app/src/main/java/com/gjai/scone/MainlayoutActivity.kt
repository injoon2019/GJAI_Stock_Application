package com.gjai.scone

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import kotlinx.android.synthetic.main.activity_main_layout.*
import kotlinx.android.synthetic.main.activity_main_rank.*
import kotlinx.android.synthetic.main.info_toolbar.*

class MainlayoutActivity : AppCompatActivity() {

    private var news_list: Int = 0
    var rank_viewList = ArrayList<View>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_layout)

        setSupportActionBar(info_layout_toolbar) // 툴바를 액티비티의 앱바로 지정

        // 알림 카드 클릭했을 때, NotificationActivity로 이동
        main_info_card.setOnClickListener {
            startActivity(Intent(this, NotificationActivity::class.java))
        }

//        present_back_button.setOnClickListener {
//            startActivity(Intent(this, InfoActivity::class.java))
//            finish()
//        }
//
//        var main_info_List = arrayOf<Info>(
//            Info("LG전자", "야심차게 준비한  이것 도대체 무엇이길래 사람들이 주목할까?", "lg_content"),
//            Info("넷마블", "클라우드 게임 본격화! 나에게 맞는 게임은?", "netmarble_icon"),
//            Info("SK하이닉스", "사상 최대 10조원 빅딜 인텔 낸드플래시 인수", "skhynix_content"),
//            Info("삼성전자", "삼성 이건희 회장 별세", "samsunt_electronics_content"),
//            Info("카카오", "카카오 스콘과 인수 합병", "kakao_content")
//        )
//
//
//        val RV_adapter = RecyclerAdapterInfo(this, main_info_List)
//        xml_info_rv.adapter = RV_adapter
//
//
//        //SwipeRefresh 구현 부분
//        srl_main.setOnRefreshListener {
//            // 사용자가 아래로 드래그 했다가 놓았을 때 호출 됩니다.
//            // 이때 새로고침 화살표가 계속 돌아갑니다.
//            // 서버통신~~
//            var main_info_SwipeRefresh_List =
//                arrayOf<Info>(Info("솔트룩스", "솔트룩스, AI EXPO에 스페이스 오딧세이의 모노리스 선보여", "saltlux"))
//            if (news_list == 0) {
//                main_info_SwipeRefresh_List = arrayOf<Info>(
//                    Info("솔트룩스", "솔트룩스, AI EXPO에 스페이스 오딧세이의 모노리스 선보여", "saltlux"),
//                    Info("JYP엔터테인먼트", "엔터테인먼트주 강세, 초록뱀 4%대 오르고 JYP YG SM 빅히트 상승", "jyp_content"),
//                    Info("한글과컴퓨터", "한글과컴퓨터, 한컴구름 전용 '한글2020' 베타버전 공개", "hangle_content"),
//                    Info("LG화학", "LG화학, 온택트 사회공헌 활동 ‘LIKE GREEN’ 실시", "lg_ch_content"),
//                    Info("빅히트", "빅히트, 4Q깜짝 실적 기대…목표가", "bighit_content")
//                )
//                news_list = 1
//            } else {
//                main_info_SwipeRefresh_List = arrayOf<Info>(
//                    Info("LG전자", "야심차게 준비한  이것 도대체 무엇이길래 사람들이 주목할까?", "lg_content"),
//                    Info("넷마블", "클라우드 게임 본격화! 나에게 맞는 게임은?", "netmarble_icon"),
//                    Info("SK하이닉스", "사상 최대 10조원 빅딜 인텔 낸드플래시 인수", "skhynix_content"),
//                    Info("삼성전자", "삼성 이건희 회장 별세", "samsunt_electronics_content"),
//                    Info("카카오", "카카오 스콘과 인수 합병", "kakao_content")
//                )
//                news_list = 0
//            }
//
//            val SR_adapter = RecyclerAdapterInfo(this, main_info_SwipeRefresh_List)
//            xml_info_rv.adapter = SR_adapter
//
//            srl_main.isRefreshing = false //서버 통신 완료 후 호출해줍니다.
//        }
    }
}

//class Info(val main_title: String, val main_content: String, val main_image: String)

