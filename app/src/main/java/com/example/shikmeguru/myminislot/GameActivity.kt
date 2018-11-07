package com.example.shikmeguru.myminislot

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.ImageButton
import kotlinx.android.synthetic.main.activity_game.*


class GameActivity : AppCompatActivity() {
    //回したかどうかのチェック用
    var check: Array<String> = arrayOf("回していいよ！", "回していいよ！", "回していいよ！")

    //カウントが３までいったらリザルト処理
    var slotcount = 0

    var Judgment: Array<Int> = arrayOf(
        0, 0, 0
    )


    var imageArray: Array<Int> = arrayOf(
        R.drawable.seven,
        R.drawable.bigwin,
        R.drawable.bar,
        R.drawable.waltermelon,
        R.drawable.banana,
        R.drawable.cherry,
        R.drawable.grape,
        R.drawable.lemon,
        R.drawable.orange
    )

    var rate: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        backbotton.setOnClickListener { finish() }

        slot1.setOnClickListener { Roll(1, slot1) }
        slot2.setOnClickListener { Roll(2, slot2) }
        slot3.setOnClickListener { Roll(3, slot3) }

        rate = intent.getIntExtra("RATE", 10)

        ratetxt.setText(rate.toString())
    }

    override fun onResume() {
        super.onResume()
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        MyCoin.setText(pref.getInt("MY_COIN", 1000).toString())

    }

    fun saveDate(wincoin: Int) {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val mycoin = pref.getInt("MY_COIN", 1000)
        val editor = pref.edit()
        editor.putInt("MY_COIN", mycoin + wincoin)
            .apply()
        MyCoin.setText(mycoin.toString())
    }


    fun Roll(Number: Int, slot: ImageButton) {
        if (check[Number - 1] == "回していいよ！") {

            val rand = (Math.random() * imageArray.size).toInt()

            slot.setImageResource(imageArray[rand])

            Judgment[Number - 1] = rand


            check[Number - 1] = "ズルはダメだよ！"

            slotcount++
        }
        if (slotcount == 3) {
            result(Judgment, rate)
        }
    }

    fun result(Judge: Array<Int>, rate: Int) {
        if (Judge[0] == Judge[1] && Judge[1] == Judge[2]) {
            when (Judge[0]) {
                0 -> saveDate(rate * 20)
                1 -> saveDate(rate * 10)
                2 -> saveDate(rate * 5)
                else -> saveDate(rate * 2)
            }
        }else if(Judge[0] == Judge[1] || Judge[0] == Judge[2]) {
            when (Judge[0]) {
                0 -> saveDate(rate * 3)
                else -> saveDate(rate)
            }

        }else if(Judge[1] == Judge[2]) {
            when (Judge[1]) {
                0 -> saveDate(rate * 3)
                else -> saveDate(rate)
            }
        }else if(Judge[0] == 8 || Judge[1] == 5 || Judge[2] == 7) {
            saveDate(rate*30)
        }else if(Judge[0] == 3 && Judge[1] == 4 && Judge[2] == 6){
            saveDate(rate*10)
        }
        saveDate(rate*-1)
    }

}




