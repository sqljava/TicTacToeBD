package com.example.tictactoebd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private lateinit var startbtn : Button
    private lateinit var player1 : EditText
    private lateinit var player2 : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startbtn = findViewById(R.id.start)
        player1 = findViewById(R.id.enterplayer1)
        player2 = findViewById(R.id.enterplayer2)




        startbtn.setOnClickListener {
            var player1name = player1.text.toString()
            var player2name = player2.text.toString()



            //var players: ArrayList<Players> = arrayListOf(Players(player1name,0),Players(player2name,0))

            val startGame = Intent(this, GameActivity::class.java)

            startGame.putExtra(Intent.EXTRA_TEXT,player1name)
//            startGame.putExtra("oyinchi1",player2name)

            startActivity(startGame)
        }
    }
}