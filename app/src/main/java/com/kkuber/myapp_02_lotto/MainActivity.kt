package com.kkuber.myapp_02_lotto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private var didRun = false
    private val pickNumberSet = hashSetOf<Int>()

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

    private val numberTextViewList : List<TextView> by lazy {
        listOf<TextView>(
            findViewById<TextView>(R.id.textView1),
            findViewById<TextView>(R.id.textView2),
            findViewById<TextView>(R.id.textView3),
            findViewById<TextView>(R.id.textView4),
            findViewById<TextView>(R.id.textView5),
            findViewById<TextView>(R.id.textView6)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberPicker.minValue = 1
        numberPicker.maxValue = 45

        initRunButton()
        initAddButton()
    }

    private fun initAddButton() {
        addButton.setOnClickListener {
            if (didRun) {
                Toast.makeText(this, "초기화 버튼을 누른 뒤 시도해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (pickNumberSet.size >= 6) {
                Toast.makeText(this, "번호는 6개까지만 선택할 수 있습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (pickNumberSet.contains(numberPicker.value)) {
                Toast.makeText(this, "이미 선택된 번호 입니다. 다른 번호를 선택해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val textView = numberTextViewList[pickNumberSet.size]
            textView.isVisible = true
            textView.text = numberPicker.value.toString()

            pickNumberSet.add(numberPicker.value)
        }
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