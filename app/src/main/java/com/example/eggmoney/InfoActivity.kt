package com.example.eggmoney

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatViewInflater
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_info.*
import kotlinx.android.synthetic.main.info_drawer_header.*
import kotlinx.android.synthetic.main.info_drawer_header.view.*
import kotlinx.android.synthetic.main.info_toolbar.*
import org.w3c.dom.Text

import androidx.recyclerview.widget.LinearLayoutManager



class InfoActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener {
    //firebase Auth
    private lateinit var firebaseAuth: FirebaseAuth

    //google client
    private lateinit var googleSignInClient: GoogleSignInClient

    //recyclerView
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        recyclerView = findViewById(R.id.recyclerView) // recyclerView 부분
        recyclerView.adapter = MainRecyclerViewAdapter()
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        var suggestion = arrayOf("삼성전자", "LG전자", "나무기술")  // 자동완성 검색기능 부분
        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, suggestion)
        autocomplete_stock.threshold = 2
        autocomplete_stock.setAdapter(adapter)


        setSupportActionBar(info_layout_toolbar) // 툴바를 액티비티의 앱바로 지정
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // 드로어를 꺼낼 홈 버튼 활성화
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24) // 홈버튼 이미지 변경
        supportActionBar?.setDisplayShowTitleEnabled(false) // 툴바에 타이틀 안보이게

        main_navigationView.setNavigationItemSelectedListener(this)

        //네비게이션 헤더 가져오는 부분
        val navigationView: NavigationView = findViewById(R.id.main_navigationView)
        val headerView: View = navigationView.getHeaderView(0)
        val navUsername: TextView = headerView.findViewById(R.id.user_name)
        val navUserEmail: TextView = headerView.findViewById(R.id.user_email)


        //Google 로그인 옵션 구성. requestIdToken 및 Email 요청
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            //'R.string.default_web_client_id' 에는 본인의 클라이언트 아이디를 넣어주시면 됩니다.
            //저는 스트링을 따로 빼서 저렇게 사용했지만 스트링을 통째로 넣으셔도 됩니다.
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        //firebase auth 객체
        firebaseAuth = FirebaseAuth.getInstance()


        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            // Name, email address, and profile photo Url
            val name = user.displayName
            val email = user.email
            val photoUrl = user.photoUrl

            // Check if user's email is verified
            //이메일 인증하기
            val emailVerified = user.isEmailVerified
            if (!emailVerified) {
                Toast.makeText(this, "이메일 인증을 완료해주세요", Toast.LENGTH_SHORT).show()
                user?.sendEmailVerification()
                    ?.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d("TAG", "Email sent.")
                        }
                    }
            }


            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            val uid = user.uid

        }

        //네비게이션 드로어 헤더에 사용자 정보로 보여주는 부분
        navUsername.text = user?.displayName.toString()
        navUserEmail.text = user?.email.toString()


    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> { // 메뉴 버튼
                main_drawer_layout.openDrawer(GravityCompat.START)    // 네비게이션 드로어 열기
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val user = FirebaseAuth.getInstance().currentUser

        when (item.itemId) {
            R.id.account -> Toast.makeText(this, "account clicked", Toast.LENGTH_SHORT).show() //내정보
            R.id.item2 -> Toast.makeText(this, "item2 clicked", Toast.LENGTH_SHORT).show() //선물함
            R.id.item3 -> {  //쿠폰 등록
                startActivity(Intent(this, RegisterCouponActivity::class.java))
            }
            R.id.item2_1 -> Toast.makeText(this, "환경정보", Toast.LENGTH_SHORT).show() //환경정보
            R.id.item2_2 -> signOut() //로그아웃
        }
        return false
    }

    override fun onBackPressed() { //뒤로가기 처리
        if (main_drawer_layout.isDrawerOpen(GravityCompat.START)) {
            main_drawer_layout.closeDrawers()
            // 테스트를 위해 뒤로가기 버튼시 Toast 메시지
            Toast.makeText(this, "back btn clicked", Toast.LENGTH_SHORT).show()
        } else {
            super.onBackPressed()
        }
    }

    private fun signOut() { // 로그아웃
        // Firebase sign out
        firebaseAuth.signOut()

        // Google sign out
        googleSignInClient.signOut().addOnCompleteListener(this) {
            //updateUI(null)
        }

        startActivity(Intent(this, GoogleSignInActivity::class.java))
        finish()
    }

    private class MainRecyclerViewAdapter :
        RecyclerView.Adapter<MainRecyclerViewViewHolder>() { //RecyclerView 부분
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): MainRecyclerViewViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recyclerview, parent, false)
            return MainRecyclerViewViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: MainRecyclerViewViewHolder, position: Int) {
            holder.setTitle((position + 1).toString() + "번째 아이템입니다.")
        }

        override fun getItemCount(): Int {
            return 10
        }
    }

    private class MainRecyclerViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val title: TextView = itemView.findViewById(R.id.title)

        fun setTitle(title: String) {
            this.title.text = title
        }

        private class DivideDecoration(context: Context) : RecyclerView.ItemDecoration() {

            private val paint: Paint = Paint()

            init {
                paint.strokeWidth = context.resources.displayMetrics.density * 5
            }

            override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
                for (i in 0 until parent.childCount) {
                    val view = parent.getChildAt(i)
                    c.drawLine(
                        view.left.toFloat(),
                        view.bottom.toFloat(),
                        view.right.toFloat(),
                        view.bottom.toFloat(),
                        paint
                    )
                }
            }
        }
    }
}
