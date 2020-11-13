package com.gjai.scone.main_rank_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gjai.scone.R
import kotlinx.android.synthetic.main.fragment_fifty_age.*

class FiftyAgeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_fifty_age, container, false)
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
        fun newInstance(Number: Int): FiftyAgeFragment {
            return FiftyAgeFragment().apply {
                arguments = Bundle().apply {
                    putInt(num, Number)
                }
            }
        }
    }
}