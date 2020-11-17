package com.gjai.scone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_questionnaire.*

class QuestionnaireActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questionnaire)

        questionnaire_next_button.setOnClickListener {
            startActivity(Intent(this, InfoActivity::class.java))
        }
    }
}