package com.example.quizkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import com.example.quizkotlin.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private lateinit var anim : Animation
    private val SPLASH_DISPLAY_LENGTH : Int = 2090
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_splash)
        anim = AnimationUtils.loadAnimation(this,R.anim.alpha)
        anim.reset()
        binding.mainLayout.clearAnimation()
        anim = AnimationUtils.loadAnimation(this,R.anim.translate)
        binding.textView2.clearAnimation()
        binding.textView2.startAnimation(anim)
        Handler().postDelayed(object : Runnable{
            override fun run() {
                val intent:Intent =Intent(this@SplashActivity,LoginActivity::class.java)
                intent.flags =Intent.FLAG_ACTIVITY_NO_ANIMATION
                startActivity(intent)
                finish()
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }
        }, SPLASH_DISPLAY_LENGTH.toLong())
    }
}