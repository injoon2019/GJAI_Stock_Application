package com.gjai.scone.main_rank_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gjai.scone.R
import kotlinx.android.synthetic.main.fragment_ten_age.*
import kotlinx.android.synthetic.main.info_main_layout.*
import kotlinx.android.synthetic.main.info_main_layout.imageView


class TenAgeFragment : Fragment() {
    // 뷰 생성 <onCreate 다음에 호출>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ten_age, container, false)
        return view
    }

    // 뷰 생성이 완료되면 호출되는 메소드
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        section_label.text = arguments?.let {
            it.getInt(num).toString()
        }
        imageView.setImageResource(R.drawable.funny)
    }

    companion object {
        private const val num = "num"

        @JvmStatic
        fun newInstance(Number: Int): TenAgeFragment {
            return TenAgeFragment().apply {
                arguments = Bundle().apply {
                    putInt(num, Number)
                }
            }
        }
    }
}