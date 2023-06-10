package com.example.android.varsfit

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView

class splash_screen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val backgroundimg : ImageView = findViewById(R.id.logoid)
        val sideAnimation = AnimationUtils.loadAnimation(this,R.anim.slide)
        backgroundimg.startAnimation(sideAnimation)

        Handler().postDelayed({intent = Intent(this@splash_screen, login_page::class.java)
            startActivity(intent)
            finish()
        },1600)
    }
}