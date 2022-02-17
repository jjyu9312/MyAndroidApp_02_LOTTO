package com.kkuber.myapp_02_lotto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.NumberPicker

class MainActivity : AppCompatActivity() {

    private val clearButton : Button by lazy {
        findViewById<Button>(R.id.clearButton)
    }

    private val addButton : Button by lazy {
        findViewById<Button>(R.id.addButton)
    }

    private val runButton : Button by lazy {
        findViewById<Button>(R.id.runButton)
    }

    private val numberPicker : NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberPicker.minValue = 1
        numberPicker.maxValue = 45

        initRunButton()
    }

    private fun initRunButton() {
        runButton.setOnClickListener {
            val list = getRandomNumber()

            Log.d("MainActivity", "initRunButton : ${list.toString()}");
        }
    }

    private fun getRandomNumber(): List<Int> {

        /* 로또 번호 만드는 방법 3가지
        - MutableListOf 안에 번호가 동일하면 if문 continue
        - MutableSetOf로 add 이용
        - mutableListOf<Int>().apply 이용하여 list를 shuffle한 후 sublist로 6개까지만 출력
        */

        val numberList = mutableListOf<Int>().apply {
            for (i in 1..45) {
                this.add(i)
            }
        }
        numberList.shuffle()

        val newList = numberList.subList(0, 6)

        return newList.sorted()
    }
}