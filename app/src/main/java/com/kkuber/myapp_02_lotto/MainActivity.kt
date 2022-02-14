package com.kkuber.myapp_02_lotto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /* 로또 번호 만드는 방법 3가지
    - MutableListOf 안에 번호가 동일하면 if문 continue
    - MutableSetOf로 add 이용
    - mutableListOf<Int>().apply 이용하여 list를 shuffle한 후 sublist로 6개까지만 출력
     */
}