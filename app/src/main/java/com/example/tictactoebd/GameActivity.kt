package com.example.tictactoebd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class GameActivity : AppCompatActivity(), OnClickListener {

    lateinit var img0 :ImageView
    lateinit var img1 :ImageView
    lateinit var img2 :ImageView
    lateinit var img3 :ImageView
    lateinit var img4 :ImageView
    lateinit var img5 :ImageView
    lateinit var img6 :ImageView
    lateinit var img7 :ImageView
    lateinit var img8 :ImageView

    lateinit var textPlayer :TextView
    lateinit var player1 :TextView
    lateinit var player2 :TextView
    lateinit var player1Score :TextView
    lateinit var player2Score :TextView

    lateinit var restartbtn : Button

    var bosilgan = 0

    var players : ArrayList<Players> = arrayListOf(Players("",0),
        Players("",0))

    var matrix = Array(3) { IntArray(3) { -1 } }
    var xGalimi = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        setUI()

        var player1name = intent.getStringExtra("NAME1")
        var player2name = intent.getStringExtra("NAME2")

        players[0].name = player1name.toString()
        players[1].name = player2name.toString()


        player1.text = players[0].name
        player2.text = players[1].name

        player1Score.text = players[0].score.toString()
        player2Score.text = players[1].score.toString()

        textPlayer.text = players[0].name

        // x=0
        // o=1

        img0.setOnClickListener(this)
        img1.setOnClickListener(this)
        img2.setOnClickListener(this)
        img3.setOnClickListener(this)
        img4.setOnClickListener(this)
        img5.setOnClickListener(this)
        img6.setOnClickListener(this)
        img7.setOnClickListener(this)
        img8.setOnClickListener(this)

        restartbtn.setOnClickListener {
            restart()
        }

    }

    override fun onClick(v: View?) {
        var img = findViewById<ImageView>(v!!.id)
        var tag = img.tag.toString().toInt()
        var col:Int = tag/3
        var row:Int = tag%3

        if (matrix[col][row]==-1){
            if (xGalimi){
                img.setImageResource(R.drawable.x)
                xGalimi = false
                matrix[col][row] = 0
                textPlayer.text = players[1].name
                bosilgan++
                isWinner(0)
                isDraw()

            }else{
                img.setImageResource(R.drawable.o)
                xGalimi = true
                matrix[col][row] = 1
                textPlayer.text = players[0].name
                bosilgan++
                isWinner(1)
                isDraw()
            }

        }
    }

    fun isDraw(){
        if (bosilgan==9){
            restartbtn.visibility = VISIBLE
            textPlayer.text = "Draw"

        }
    }

    fun isWinner(a: Int) {
        var count = 0
        for (i in 0..2) {
            for (j in 0..2) {
                if (matrix[i][j] == a) {
                    count++
                }
            }
            if (count == 3) {
                finishGame(a)
                textPlayer.text = "Winner is ${players[a].name}"
                return
            }
            count = 0
        }
        count = 0
        for (i in 0..2) {
            for (j in 0..2) {
                if (matrix[j][i] == a) {
                    count++
                }
            }
            if (count == 3) {
                finishGame(a)
                textPlayer.text = "Winner is ${players[a].name}"
                return
            }
            count = 0
        }
        count = 0
        for (i in 0..2) {
            for (j in 0..2) {
                if (i == j) {
                    if (matrix[j][i] == a) {
                        count++
                    }
                }
            }
        }
        if (count == 3) {
            finishGame(a)
            textPlayer.text = "Winner is ${players[a].name}"
            return
        }
        count = 0
        for (i in 0..2) {
            for (j in 0..2) {
                if (i + j == 2) {
                    if (matrix[j][i] == a) {
                        count++
                    }
                }
            }
        }
        if (count == 3) {
            finishGame(a)
            textPlayer.text = "Winner is ${players[a].name}"
            return
        }
    }

    fun finishGame(a:Int){

        img0.isEnabled = false
        img1.isEnabled = false
        img2.isEnabled = false
        img3.isEnabled = false
        img4.isEnabled = false
        img5.isEnabled = false
        img6.isEnabled = false
        img7.isEnabled = false
        img8.isEnabled = false

        restartbtn.visibility = View.VISIBLE

        players[a].score+=1

        player1Score.text = players[0].score.toString()
        player2Score.text = players[1].score.toString()

        bosilgan=0
    }



    fun restart() {
        matrix = Array(3) { IntArray(3) { -1 } }
        xGalimi = true

        textPlayer.text = players[0].name
        restartbtn.visibility = View.INVISIBLE

        bosilgan = 0

        img0.setImageDrawable(null)
        img1.setImageDrawable(null)
        img2.setImageDrawable(null)
        img3.setImageDrawable(null)
        img4.setImageDrawable(null)
        img5.setImageDrawable(null)
        img6.setImageDrawable(null)
        img7.setImageDrawable(null)
        img8.setImageDrawable(null)

        img0.isEnabled = true
        img1.isEnabled = true
        img2.isEnabled = true
        img3.isEnabled = true
        img4.isEnabled = true
        img5.isEnabled = true
        img6.isEnabled = true
        img7.isEnabled = true
        img8.isEnabled = true

    }

    fun setUI(){
        img0 = findViewById(R.id.img0)
        img1 = findViewById(R.id.img1)
        img2 = findViewById(R.id.img2)
        img3 = findViewById(R.id.img3)
        img4 = findViewById(R.id.img4)
        img5 = findViewById(R.id.img5)
        img6 = findViewById(R.id.img6)
        img7 = findViewById(R.id.img7)
        img8 = findViewById(R.id.img8)

        textPlayer = findViewById(R.id.textplayer)
        player1 = findViewById(R.id.player1)
        player2 = findViewById(R.id.player2)
        player1Score = findViewById(R.id.player1score)
        player2Score = findViewById(R.id.player2score)

        restartbtn = findViewById(R.id.restart)

    }
}