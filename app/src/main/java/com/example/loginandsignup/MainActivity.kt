package com.example.loginandsignup

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.loginandsignup.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        val spanableText = SpannableString("Welcome")
        spanableText.setSpan(ForegroundColorSpan(Color.parseColor("#FF0000")),0,5,0)
        spanableText.setSpan(ForegroundColorSpan(Color.parseColor("#312222")),5,spanableText.length,0)

        binding.welcomeText.text = spanableText

        val intent = Intent(this,LoginActivity::class.java)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(intent)
            finish()
        },3000)


    }
}