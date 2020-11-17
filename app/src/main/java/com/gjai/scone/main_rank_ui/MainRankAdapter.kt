package com.gjai.scone.main_rank_ui

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.fragment.app.FragmentPagerAdapter
import com.gjai.scone.main_rank_ui.TenAgeFragment

class MainRankAdapter (fm: FragmentManager) : FragmentStatePagerAdapter(fm){
    // 프레그먼트 담는 리스트
    private val items= ArrayList<Fragment>()
    init {
    // 프레그먼트 생성 후 리스트에 저장
        items.add(TenAgeFragment.newInstance(1))
        items.add(TwentyAgeFragment.newInstance(2))
        items.add(ThirtyAgeFragment.newInstance(3))
        items.add(FortyAgeFragment.newInstance(4))
        items.add(FiftyAgeFragment.newInstance(5))
    }
    override fun getItem(position: Int): Fragment {
        return items[position]
    }
    override fun getCount(): Int {
        return items.size
    }
}