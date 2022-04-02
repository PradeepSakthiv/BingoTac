package com.example.bingotac

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var confirm_button: ImageView
    private lateinit var p1: EditText
    private lateinit var p2: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        p1 = findViewById(R.id.p1)
        p2 = findViewById(R.id.p2)
        confirm_button = findViewById(R.id.confirm_button)

        confirm_button.setOnClickListener {

            if (p1.text.toString().isEmpty()) {
                Toast.makeText(this, "Please enter player 1 name", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (p2.text.toString().isEmpty()) {
                Toast.makeText(this, "Please enter player 2 name", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("p1", p1.text.toString())
            intent.putExtra("p2", p2.text.toString())
            startActivity(intent)

        }

    }
}