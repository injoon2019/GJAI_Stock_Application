package com.gjai.scone.Fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.gjai.scone.*
import com.gjai.scone.RankFragments.*
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main_layout.*
import kotlinx.android.synthetic.main.activity_main_layout.view.*

class  MainFragment : Fragment() {

    private var news_list: Int = 0
    private lateinit var rankviewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    companion object {
        const val TAG : String = "로그"

        fun newInstance() : MainFragment{
            return MainFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            Log.d(TAG, "MainFragment - onCreate() called")
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        var mContext = context
        Log.d(TAG, "MainFragment - onAttach() called")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "MainFragment - onCreateView() called")

        val view = inflater.inflate(R.layout.activity_main_layout, container, false)

        val maincardinfo = view?.findViewById<ConstraintLayout>(R.id.main_info_card) //알림카드 부분
        maincardinfo?.setOnClickListener {
            v -> startActivity(Intent(getActivity(), NotificationActivity::class.java))
        }

        //실시간 rank 검색어 부분
        var main_rank_List = arrayOf<Rank>(
            Rank("LG전자", "LG전자", "LG전자", "LG전자", "LG전자", "LG전자"),
            Rank("LG전자", "LG전자", "LG전자", "LG전자", "LG전자", "LG전자"),
            Rank("LG전자", "LG전자", "LG전자", "LG전자", "LG전자", "LG전자"),
            Rank("LG전자", "LG전자", "LG전자", "LG전자", "LG전자", "LG전자"),
            Rank("LG전자", "LG전자", "LG전자", "LG전자", "LG전자", "LG전자")
        )


        tabLayout = view.findViewById(R.id.rank_tabLayout)
        rankviewPager = view.findViewById(R.id.rank_view_pager)
        rankviewPager.adapter = RankViewPagerAdapter(this, main_rank_List)

        val tabLayoutTextArray = arrayOf("10-", "20-", "30-", "40-", "50-")

        TabLayoutMediator(tabLayout,rankviewPager){tab,position->
            tab.text = tabLayoutTextArray[position]
        }.attach()

        //리사이클러뷰 부분 여기부터
        var main_info_List = arrayOf<Info>(
            Info("LG전자", "야심차게 준비한  이것 도대체 무엇이길래 사람들이 주목할까?", "lg_content"),
            Info("넷마블", "클라우드 게임 본격화! 나에게 맞는 게임은?", "netmarble_icon"),
            Info("SK하이닉스", "사상 최대 10조원 빅딜 인텔 낸드플래시 인수", "skhynix_content"),
            Info("삼성전자", "삼성 이건희 회장 별세", "samsunt_electronics_content"),
            Info("카카오", "카카오 스콘과 인수 합병", "kakao_content")
        )

        val RV_adapter = RecyclerAdapterInfoFragment(this, main_info_List)
        view.xml_info_rv.adapter = RV_adapter //엑티비티랑 다른점 view를 붙여야함

        //SwipeRefresh 구현 부분
        view.srl_main.setOnRefreshListener {
            // 사용자가 아래로 드래그 했다가 놓았을 때 호출 됩니다.
            // 이때 새로고침 화살표가 계속 돌아갑니다.
            // 서버통신~~
            var main_info_SwipeRefresh_List =
                arrayOf<Info>(Info("솔트룩스", "솔트룩스, AI EXPO에 스페이스 오딧세이의 모노리스 선보여", "saltlux"))
            if (news_list == 0) {
                main_info_SwipeRefresh_List = arrayOf<Info>(
                    Info("솔트룩스", "솔트룩스, AI EXPO에 스페이스 오딧세이의 모노리스 선보여", "saltlux"),
                    Info("JYP엔터테인먼트", "엔터테인먼트주 강세, 초록뱀 4%대 오르고 JYP YG SM 빅히트 상승", "jyp_content"),
                    Info("한글과컴퓨터", "한글과컴퓨터, 한컴구름 전용 '한글2020' 베타버전 공개", "hangle_content"),
                    Info("LG화학", "LG화학, 온택트 사회공헌 활동 ‘LIKE GREEN’ 실시", "lg_ch_content"),
                    Info("빅히트", "빅히트, 4Q깜짝 실적 기대…목표가", "bighit_content")
                )
                news_list = 1
            } else {
                main_info_SwipeRefresh_List = arrayOf<Info>(
                    Info("LG전자", "야심차게 준비한  이것 도대체 무엇이길래 사람들이 주목할까?", "lg_content"),
                    Info("넷마블", "클라우드 게임 본격화! 나에게 맞는 게임은?", "netmarble_icon"),
                    Info("SK하이닉스", "사상 최대 10조원 빅딜 인텔 낸드플래시 인수", "skhynix_content"),
                    Info("삼성전자", "삼성 이건희 회장 별세", "samsunt_electronics_content"),
                    Info("카카오", "카카오 스콘과 인수 합병", "kakao_content")
                )
                news_list = 0
            }

            val SR_adapter = RecyclerAdapterInfoFragment(this, main_info_SwipeRefresh_List)
            xml_info_rv.adapter = SR_adapter

            srl_main.isRefreshing = false //서버 통신 완료 후 호출해줍니다.
        }

        return view

    }

}
data class Info(val main_title: String, val main_content: String, val main_image: String)

data class Rank(val content1: String, val content2: String, val content3: String, val content4: String, val content5: String, val content6: String )

class RecyclerAdapterInfoFragment(val context: MainFragment, val infoList: Array<Info>) :
    RecyclerView.Adapter<RecyclerAdapterInfoFragment.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder { //context를 불러올땐 parent에서
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerview, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return infoList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bind(infoList[position], context)
    }

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        private val mainTitle = itemView?.findViewById<TextView>(R.id.info_Title)
        private val mainContent = itemView?.findViewById<TextView>(R.id.info_Content)
        private val mainImage = itemView?.findViewById<ImageView>(R.id.info_ImageView)

        fun bind(info: Info, context: MainFragment) {
            /* dogPhoto의 setImageResource에 들어갈 이미지의 id를 파일명(String)으로 찾고,
            이미지가 없는 경우 안드로이드 기본 아이콘을 표시한다.*/
            if (info.main_image != "") {
                val resourceId = context.resources.getIdentifier(
                    info.main_image,
                    "drawable",
                    context.getActivity()?.packageName
                )
                mainImage?.setImageResource(resourceId)
            } else {
                mainImage?.setImageResource(R.mipmap.ic_launcher)
            }
            /* 나머지 TextView와 String 데이터를 연결한다. */
            mainTitle?.text = info.main_title
            mainContent?.text = info.main_content
        }
    }
}

private  class RankViewPagerAdapter(val context: MainFragment, val rankList: Array<Rank>): FragmentStateAdapter(context){
    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> TenAgeFragment()
            1 -> TwentyAgeFragment()
            2 -> ThirtyAgeFragment()
            3 -> FortyAgeFragment()
            else -> FiftyAgeFragment()
        }
    }
    override fun getItemCount():Int = 5

}

