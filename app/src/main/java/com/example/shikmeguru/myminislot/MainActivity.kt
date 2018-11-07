package com.example.shikmeguru.myminislot

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
// import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var m_rate:Int = 10
    var m_mycoin:Int= -1;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        start.setOnClickListener{startBottonTap()}
        reset.setOnClickListener{resetBottanTap()}



        reteUP.setOnClickListener{RateUp()}
        reteDown.setOnClickListener{RateDown()}

        MyCoin.setText(m_mycoin.toString())

    }

    override fun onResume() {
        super.onResume()
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        m_mycoin = pref.getInt("MY_COIN", 1000)
        MyCoin.setText(m_mycoin.toString())
    }

    fun startBottonTap() {
        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra("RATE" , m_rate)
        startActivity(intent)

    }
    fun resetBottanTap() {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        pref.edit().putInt("MY_COIN",1000).apply()
        m_rate = 10
        MyCoin.setText(m_mycoin.toString())
    }



    fun RateUp(){
        if(m_mycoin>0) {
            m_rate += 10
            m_mycoin -= 10
            ratetx.setText(m_rate.toString())
            MyCoin.setText(m_mycoin.toString())
        }
    }
    fun RateDown(){
        if(m_rate>0) {
            m_rate -= 10
            m_mycoin += 10
            ratetx.setText(m_rate.toString())
            MyCoin.setText(m_mycoin.toString())
        }
    }

}
