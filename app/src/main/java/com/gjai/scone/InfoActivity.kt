package com.gjai.scone

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.gjai.scone.Fragments.*
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.activity_info.*
import kotlinx.android.synthetic.main.info_toolbar.*
import org.apache.poi.hssf.usermodel.HSSFCell
import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.poifs.filesystem.POIFSFileSystem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.InputStream


class InfoActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth //firebase Auth
    private lateinit var googleSignInClient: GoogleSignInClient //google client

    private val BaseURL: String = "https://scone-294502.du.r.appspot.com"

    private val couponRegisterFragment by lazy { CouponRegisterFragment() } // 네비게이션 탭 뷰 프레그먼트 부분
    private val mainFragment by lazy { MainFragment() }
    private val myInfoFragment by lazy { MyInfoFragment() }
    private val presentFragment by lazy { PresentFragment() }
    private val settingFragment by lazy { SettingFragment() }

    private val fragments_list: List<Fragment> = listOf(
        settingFragment, myInfoFragment, mainFragment, presentFragment, couponRegisterFragment
    )

    private val pagerAdapter: MainViewPagerAdapter by lazy {
        MainViewPagerAdapter(
            this,
            fragments_list
        )
    }

    private lateinit var stockCode: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        initViewPager()
        initNavigationBar()

        val suggestion = resources.getStringArray(R.array.stock_list)// 자동완성 검색기능 부분
        var search_adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, suggestion)
        autocomplete_stock.threshold = 1
        autocomplete_stock.setAdapter(search_adapter)

        setSupportActionBar(info_layout_toolbar) // 툴바를 액티비티의 앱바로 지정
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // 드로어를 꺼낼 홈 버튼 활성화
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24) // 홈버튼 이미지 변경
        supportActionBar?.setDisplayShowTitleEnabled(false) // 툴바에 타이틀 안보이게


//        //네비게이션 헤더 가져오는 부분
//        val navigationView: NavigationView = findViewById(R.id.main_navigationView)
//        val headerView: View = navigationView.getHeaderView(0)
//        val navUsername: TextView = headerView.findViewById(R.id.user_name)
//        val navUserEmail: TextView = headerView.findViewById(R.id.user_email)

        //Google 로그인 옵션 구성. requestIdToken 및 Email 요청
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            //'R.string.default_web_client_id' 에는 본인의 클라이언트 아이디를 넣어주시면 됩니다.
            //저는 스트링을 따로 빼서 저렇게 사용했지만 스트링을 통째로 넣으셔도 됩니다.
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        firebaseAuth = FirebaseAuth.getInstance() //firebase auth 객체

        registerPushToken() //firebase 토큰 생성

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
//        Toast.makeText(this, user?.uid.toString(), Toast.LENGTH_SHORT).show()

        //이하 네트워크 통신 코드
        val retrofit = Retrofit.Builder().baseUrl(BaseURL).addConverterFactory(
            GsonConverterFactory.create()
        ).build();
        var loginCheck = retrofit.create(LoginCheck::class.java);
        loginCheck.checkLogin(user?.uid.toString(), user?.displayName.toString())
            .enqueue(object : Callback<Login> {
                var login: Login? = null
                override fun onFailure(call: Call<Login>, t: Throwable) { //실패할 경우
                    t.message?.let { Log.e("LOGIN", it) }
                    var dialog = AlertDialog.Builder(this@InfoActivity)
                    dialog.setTitle("에러")
                    dialog.setMessage("호출 실패")
                    dialog.show()
                }

                override fun onResponse(call: Call<Login>, response: Response<Login>) { //정상응답이 올경우
                    login = response.body()
                }
            })


//        //네비게이션 드로어 헤더에 사용자 정보로 보여주는 부분
//        navUsername.text = user?.displayName.toString()
//        navUserEmail.text = user?.email.toString()

        //자동완성 클릭했을때
        autocomplete_stock.setOnItemClickListener { parent, view, position, id ->
            val item = parent.getItemAtPosition(position).toString()

            readExcelFileFromAssets(item) // stockCode 변수에 주식 코드 저장해주기
            val intent = Intent(this, SearchResultActivity::class.java)
//            Toast.makeText(this, "테스트", Toast.LENGTH_SHORT).show()
            intent.putExtra("stock_name", autocomplete_stock.text.toString())
            intent.putExtra("stock_code", stockCode)
            intent.putExtra("uid", user?.uid.toString())
            // 넘겨주기
//            readExcelFileFromAssets(autocomplete_stock.text.toString()) // csv 파일 이용해 종목 코드 넘겨주기
            startActivity(intent)
        }

        //서치 버튼 - SearchResultActivity로 검색한 종목 이름을 넘긴다
        searchRequest_button.setOnClickListener {

            readExcelFileFromAssets(autocomplete_stock.text.toString()) // stockCode 변수에 주식 코드 저장해주기
            val intent = Intent(this, SearchResultActivity::class.java)
            intent.putExtra("stock_name", autocomplete_stock.text.toString())
            intent.putExtra("stock_code", stockCode)
            intent.putExtra("uid", user?.uid.toString())
            // 넘겨주기
//            readExcelFileFromAssets(autocomplete_stock.text.toString()) // csv 파일 이용해 종목 코드 넘겨주기
            startActivity(intent)
        }
    }

    private fun readExcelFileFromAssets(value: String) {
        try {
            val myInput: InputStream // assetManager 초기 설정
            val assetManager = assets  //  엑셀 시트 열기
            myInput = assetManager.open("stock_symbol_name.xls")

            val myFileSystem = POIFSFileSystem(myInput) // POI File System 객체 만들기
            val myWorkBook = HSSFWorkbook(myFileSystem) //워크 북
            val sheet = myWorkBook.getSheetAt(0) // 워크북에서 시트 가져오기
            val rowIter = sheet.rowIterator() //행을 반복할 변수 만들어주기
            var rowno = 0 //행 넘버 변수 만들기
            var answer_array: Array<String> = arrayOf(value, "퇴근시켜줘 제발") // 확인 가능한 배열 생성

            while (rowIter.hasNext()) {
                if (answer_array[1] != "퇴근시켜줘 제발") break
                val myRow = rowIter.next() as HSSFRow
                if (rowno != 0) {
                    val cellIter = myRow.cellIterator()  //열을 반복할 변수 만들어주기
                    var colno = 0 //열 넘버 변수 만들기
                    while (cellIter.hasNext()) {  //열 반복문
                        val myCell = cellIter.next() as HSSFCell
                        if (colno === 0) {
                            if (myCell.toString() != value) break
                        }
                        if (colno === 1) {
                            answer_array[1] = myCell.toString()
                        }
                        colno++
                    }
                }
                rowno++
            }
            stockCode = answer_array[1].toString()
//            Toast.makeText(this, answer_array[1], Toast.LENGTH_LONG).show() // 확인메시지 출력
        } catch (e: Exception) {
            Toast.makeText(this, "에러 발생", Toast.LENGTH_LONG).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> { // 메뉴 버튼
                //햄버거 버튼을 누르면 키보드를 항상 숨기게 설정
                hideKeyboard()
                //main_drawer_layout.openDrawer(GravityCompat.START)    // 네비게이션 드로어 열기
            }
        }
        return super.onOptionsItemSelected(item)
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

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(autocomplete_stock.windowToken, 0);
    }

    private fun registerPushToken() {
        //v17.0.0 이전까지는
        ////var pushToken = FirebaseInstanceId.getInstance().token
        //v17.0.1 이후부터는 onTokenRefresh()-depriciated
        var pushToken: String? = null
        var uid = FirebaseAuth.getInstance().currentUser!!.uid
        var map = mutableMapOf<String, Any>()
        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener { instanceIdResult ->
            pushToken = instanceIdResult.token
            map["pushtoken"] = pushToken!!
            FirebaseFirestore.getInstance().collection("pushtokens").document(uid!!).set(map)
        }
    }

    inner class MainViewPagerAdapter(
        activity: AppCompatActivity,
        private val fragments: List<Fragment>
    ) : FragmentStateAdapter(activity) {

        override fun getItemCount(): Int = fragments.size
        override fun createFragment(position: Int): Fragment = fragments[position]

    }

    private fun initNavigationBar() {
        bottomNavigationView.run {
            setOnNavigationItemSelectedListener {
                val page = when (it.itemId) {
                    R.id.item0 -> 0
                    R.id.item1 -> 1
                    R.id.item2 -> 2
                    R.id.item3 -> 3
                    R.id.item4 -> 4
                    else -> 2
                }
                if (page != main_viewpager.currentItem) {
                    main_viewpager.currentItem = page
                }
                true

            }
            selectedItemId = R.id.item2
        }
    }

    private fun initViewPager() {
        main_viewpager.run {
            adapter = pagerAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    val navigation = when (position) {
                        0 -> R.id.item0
                        1 -> R.id.item1
                        2 -> R.id.item2
                        3 -> R.id.item3
                        4 -> R.id.item4
                        else -> R.id.item2
                    }
                    if (bottomNavigationView.selectedItemId != navigation) {
                        bottomNavigationView.selectedItemId = navigation
                    }
                }
            })
        }
    }
}
