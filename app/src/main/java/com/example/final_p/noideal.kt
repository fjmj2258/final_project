package com.example.final_p

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.Button


class noideal : AppCompatActivity() {

    //食物名單
    val breakfastlist = arrayOf("7-11", "全家", "萊爾富", "OK", "麥當勞",
        "肯德基", "漢堡王","燕麥","優格","水煮蛋","沙拉","蘿蔔糕","鐵板麵",
        "蛋吐司","厚片吐司","三明治","豆漿","米漿","燒餅油條","奶茶","蛋餅","漢堡",
        "蔥抓餅","玉米濃湯","煎餃","飯糰","麵線","饅頭")
    val lunchlist = arrayOf("北科學餐","義大利麵","拉麵","炒麵","炒飯","豬血湯",
        "粥","肉燥飯","虱目魚湯","油飯","便當","自助餐","乾麵","牛肉麵","速食","pizza",
        "沙拉","咖哩飯","御飯糰","關東煮","烏龍麵","壽司","微波食品","麵包","泡麵",
        "小火鍋")
    val dinnerlist = arrayOf("牛肉麵","滷味","麥當勞","蛋包飯","章魚燒","不吃",
        "涼麵","水餃","粥","羹","麵線","麻油雞","排骨湯","蛋炒飯","燉飯","沙拉",
        "滷豬腳飯","鹹派","義大利麵","烤雞","炸雞")


    //存取食物名單長度
    var breakfastlistsize = breakfastlist.size
    val lunchlistsize = lunchlist.size
    val dinnerlistsize = dinnerlist.size

    //判定按鈕狀況
    var breakfastflag = 0
    var lunchflag = 0
    var dinnerflag = 0

    //存取上次顯示食物
    var breakfastlastshow = breakfastlist[(0..breakfastlistsize - 1).random()]
    var lunchlastshow = lunchlist[(0..lunchlistsize - 1).random()]
    var dinnerlastshow = dinnerlist[(0..dinnerlistsize - 1).random()]

    //存取下次顯示食物
    var breakfastnextshow = breakfastlist[(0..breakfastlistsize - 1).random()]
    var lunchnextshow = lunchlist[(0..lunchlistsize - 1).random()]
    var dinnernextshow = dinnerlist[(0..dinnerlistsize - 1).random()]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noideal)

        //返回按鈕觸發 -> 跳回主畫面
        val btn_noideal_back = findViewById<Button>(R.id.btn_noideal_back)
        btn_noideal_back.setOnClickListener { startActivityForResult(Intent(this, MainActivity::class.java), 1) }

        // -----------------------------------------------------------------------------------------------

        //早餐 點第一次跑動畫,點第二次暫停
        val btn_breakfast = findViewById<Button>(R.id.btn_breakfast)
        btn_breakfast.setOnClickListener {
            if (breakfastflag == 0) {
                breakfastflag = 1
                breakfeastfoodcycle()
            } else {
                breakfastflag = 0
                return@setOnClickListener
            }
        }

        // -----------------------------------------------------------------------------------------------

        val btn_lunch = findViewById<Button>(R.id.btn_lunch)
        btn_lunch.setOnClickListener {
            if (lunchflag == 0) {
                lunchflag = 1
                lunchfoodcycle()
            } else {
                lunchflag = 0
                return@setOnClickListener
            }
        }

        // -----------------------------------------------------------------------------------------------

        val btn_dinner = findViewById<Button>(R.id.btn_dinner)
        btn_dinner.setOnClickListener {
            if (dinnerflag == 0) {
                dinnerflag = 1
                dinnerfoodcycle()
            } else {
                dinnerflag = 0
                return@setOnClickListener
            }
        }

        // -----------------------------------------------------------------------------------------------

    }

    // -----------------------------------------------------------------------------------------------

    //使用handler處理UI顯示改變
    private val breakfasthandler = Handler(Handler.Callback { msg ->
        when (msg.what) {
            1 -> {
                val breakfurstbutton = findViewById<Button>(R.id.btn_breakfast)
                breakfastnextshow = breakfastlist[(0..breakfastlistsize - 1).random()]
                //避免兩次隨機選到相同食物,使用if-else做篩選,若相同重跑random直到不同
                while(breakfastlastshow != breakfastnextshow)
                {
                    if (breakfastlastshow == breakfastnextshow)
                    { breakfastnextshow = breakfastlist[(0..breakfastlistsize - 1).random()] }
                    else
                    { breakfurstbutton.setText(breakfastnextshow)
                        breakfastlastshow = breakfastnextshow
                    }
                } } }
        true })

    //若按鈕狀況為1保持循環
    fun breakfeastfoodcycle() {
        Thread(Runnable {
            while(breakfastflag!=0){
                val msg = Message()
                msg.what = 1
                breakfasthandler.sendMessage(msg)
                Thread.sleep(75)
            }}).start() }

    // -----------------------------------------------------------------------------------------------

    //使用handler處理UI顯示改變
    private val lunchhandler = Handler(Handler.Callback { msg ->
        when (msg.what) {
            1 -> {
                val lunchbutton = findViewById<Button>(R.id.btn_lunch)
                lunchnextshow = lunchlist[(0..lunchlistsize - 1).random()]
                //避免兩次隨機選到相同食物,使用if-else做篩選,若相同重跑random直到不同
                while(lunchlastshow != lunchnextshow)
                {
                    if (lunchlastshow == lunchnextshow)
                    { lunchnextshow = lunchlist[(0..lunchlistsize - 1).random()] }
                    else
                    { lunchbutton.setText(lunchnextshow)
                        lunchlastshow = lunchnextshow }
                } } }
        true })

    //若按鈕狀況為1保持循環
    private fun lunchfoodcycle() {
        Thread(Runnable {
            while(lunchflag!=0){
                val msg = Message()
                msg.what = 1
                lunchhandler.sendMessage(msg)
                Thread.sleep(75)
            }}).start() }

    // -----------------------------------------------------------------------------------------------

    //使用handler處理UI顯示改變
    private val dinnerhandler = Handler(Handler.Callback { msg ->
        when (msg.what) {
            1 -> {
                val dinnerbutton = findViewById<Button>(R.id.btn_dinner)
                dinnernextshow = dinnerlist[(0..dinnerlistsize - 1).random()]
                //避免兩次隨機選到相同食物,使用if-else做篩選,若相同重跑random直到不同
                while(dinnerlastshow != dinnernextshow)
                {
                    if (dinnerlastshow == dinnernextshow)
                    { dinnernextshow = dinnerlist[(0..dinnerlistsize - 1).random()] }
                    else
                    { dinnerbutton.setText(dinnernextshow)
                        dinnerlastshow = dinnernextshow }
                } } }
        true })

    //若按鈕狀況為1保持循環
    private fun dinnerfoodcycle() {
        Thread(Runnable {
            while(dinnerflag!=0){
                val msg = Message()
                msg.what = 1
                dinnerhandler.sendMessage(msg)
                Thread.sleep(75)
            }}).start() }

    // -----------------------------------------------------------------------------------------------
}