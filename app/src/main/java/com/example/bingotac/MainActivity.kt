package com.example.bingotac

import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var playerOne: Boolean = true
    private var playerTwo: Boolean = false

    private var playerOneName: String = ""
    private var playerTwoName: String = ""

    private lateinit var turn: TextView
    private lateinit var im1: TextView
    private lateinit var im2: TextView
    private lateinit var im3: TextView
    private lateinit var im4: TextView
    private lateinit var im5: TextView
    private lateinit var im6: TextView
    private lateinit var im7: TextView
    private lateinit var im8: TextView
    private lateinit var im9: TextView

    private lateinit var pt1: TextView
    private lateinit var pt2: TextView

    private lateinit var clear: Button

    // put all win positions in a 2D array
    var winPositions = arrayOf(
        intArrayOf(0, 1, 2),
        intArrayOf(3, 4, 5),
        intArrayOf(6, 7, 8),
        intArrayOf(0, 3, 6),
        intArrayOf(1, 4, 7),
        intArrayOf(2, 5, 8),
        intArrayOf(0, 4, 8),
        intArrayOf(2, 4, 6)
    )

    // Player representation
    // 1 - Player 1
    // 2 - Player 2
    var activePlayer = 1
    var count = 0
    var gameState = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        playerOneName = intent.getStringExtra("p1").toString()
        playerTwoName = intent.getStringExtra("p2").toString()

        turn = findViewById(R.id.turn)
        pt1 = findViewById(R.id.pt1)
        pt2 = findViewById(R.id.pt2)
        im1 = findViewById(R.id.im1)
        im2 = findViewById(R.id.im2)
        im3 = findViewById(R.id.im3)
        im4 = findViewById(R.id.im4)
        im5 = findViewById(R.id.im5)
        im6 = findViewById(R.id.im6)
        im7 = findViewById(R.id.im7)
        im8 = findViewById(R.id.im8)
        im9 = findViewById(R.id.im9)
        clear = findViewById(R.id.clear)

        pt1.text = playerOneName
        pt2.text = playerTwoName

        im1.setOnClickListener {
            setBoxClick(im1, 1)
        }
        im2.setOnClickListener {
            setBoxClick(im2, 2)
        }
        im3.setOnClickListener {
            setBoxClick(im3, 3)
        }
        im4.setOnClickListener {
            setBoxClick(im4, 4)
        }
        im5.setOnClickListener {
            setBoxClick(im5, 5)
        }
        im6.setOnClickListener {
            setBoxClick(im6, 6)
        }
        im7.setOnClickListener {
            setBoxClick(im7, 7)
        }
        im8.setOnClickListener {
            setBoxClick(im8, 8)
        }
        im9.setOnClickListener {
            setBoxClick(im9, 9)
        }

        clear.setOnClickListener {
            reset()
        }

        turn.text = "$playerOneName your turn"

    }

    private fun setBoxClick(im: TextView?, value: Int) {
        count++
        if (playerOne) {
            im!!.text = playerOneName.substring(0, 1)
            im.isEnabled = false
            playerOne = false
            playerTwo = true
            activePlayer = 1
            turn.text = "$playerTwoName your turn"
            gameState[value - 1] = activePlayer

            if (count >= 5) {
                checkWinStatus()
            }
        } else {
            im!!.text = playerTwoName.substring(0, 1)
            im.isEnabled = false
            playerTwo = false
            playerOne = true
            activePlayer = 2
            gameState[value - 1] = activePlayer
            turn.text = "$playerOneName your turn"
            if (count >= 5) {
                checkWinStatus()
            }
        }

    }

    private fun checkWinStatus() {
        var flag = 0

        // Check if any player has won
        for (winPosition in winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]]
                && gameState[winPosition[1]] == gameState[winPosition[2]] &&
                gameState[winPosition[0]] != 0
            ) {

                greencolor(winPosition[0])
                greencolor(winPosition[1])
                greencolor(winPosition[2])

                flag = 1
                val winnerStr: String = if (gameState[winPosition[0]] == 1) {
                    "$playerOneName has won"
                } else {
                    "$playerTwoName has won"
                }
                turn.text = winnerStr
                disableContent()
            }
        }

        if (flag == 0 && count == 8) {
            turn.text = "Match Draw "
            disableContent()
        }
    }

    private fun greencolor(position: Int) {
        when (position) {
            0 -> {
                im1.setBackgroundResource(R.drawable.button_shape)
                im1.setTextColor(resources.getColor(R.color.white))

            }
            1 -> {
                im2.setBackgroundResource(R.drawable.button_shape)
                im2.setTextColor(resources.getColor(R.color.white))
            }
            2 -> {
                im3.setBackgroundResource(R.drawable.button_shape)
                im3.setTextColor(resources.getColor(R.color.white))
            }
            3 -> {
                im4.setBackgroundResource(R.drawable.button_shape)
                im4.setTextColor(resources.getColor(R.color.white))
            }
            4 -> {
                im5.setBackgroundResource(R.drawable.button_shape)
                im5.setTextColor(resources.getColor(R.color.white))
            }
            5 -> {
                im6.setBackgroundResource(R.drawable.button_shape)
                im6.setTextColor(resources.getColor(R.color.white))
            }
            6 -> {
                im7.setBackgroundResource(R.drawable.button_shape)
                im7.setTextColor(resources.getColor(R.color.white))
            }
            7 -> {
                im8.setBackgroundResource(R.drawable.button_shape)
                im8.setTextColor(resources.getColor(R.color.white))
            }
            8 -> {
                im9.setBackgroundResource(R.drawable.button_shape)
                im9.setTextColor(resources.getColor(R.color.white))
            }
        }
    }

    private fun reset() {
        count = 0
        activePlayer = 1
        playerOne = true
        playerTwo = false
        for (i in gameState.indices) {
            gameState[i] = 0
        }

        enableContent(im1)
        enableContent(im2)
        enableContent(im3)
        enableContent(im4)
        enableContent(im5)
        enableContent(im6)
        enableContent(im7)
        enableContent(im8)
        enableContent(im9)

        turn.text = "$playerOneName your turn"

    }

    private fun enableContent(im: TextView) {
        im.text = ""
        im.isEnabled = true
        im.setBackgroundResource(R.drawable.white_shape)
        im.setTextColor(resources.getColor(R.color.black))
    }

    private fun disableContent() {
        im1.isEnabled = false
        im2.isEnabled = false
        im3.isEnabled = false
        im4.isEnabled = false
        im5.isEnabled = false
        im6.isEnabled = false
        im7.isEnabled = false
        im8.isEnabled = false
        im9.isEnabled = false
    }
}
