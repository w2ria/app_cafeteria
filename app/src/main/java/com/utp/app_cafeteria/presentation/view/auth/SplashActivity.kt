package com.utp.app_cafeteria.presentation.view.auth

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.utp.app_cafeteria.MainActivity
import com.utp.app_cafeteria.R
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {

    private val splashJob = Job()
    private val splashScope = CoroutineScope(Dispatchers.Main + splashJob)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        val topBanner: ImageView = findViewById(R.id.imageView2)
        val bottomBanner: ImageView = findViewById(R.id.imageView5)
        val logo: ImageView = findViewById(R.id.imageView)
        val lottieAnimation: LottieAnimationView = findViewById(R.id.animationView)
        val sloganText: TextView = findViewById(R.id.textView4)


        val bannerInLeft = AnimationUtils.loadAnimation(this, R.anim.banner_in_left)
        val bannerInRight = AnimationUtils.loadAnimation(this, R.anim.banner_in_right)
        val zoomLogo = AnimationUtils.loadAnimation(this, R.anim.zoom_logo)
        val fadeInLottie = AnimationUtils.loadAnimation(this, R.anim.fade_in_lottie)


        topBanner.startAnimation(bannerInLeft)
        bottomBanner.startAnimation(bannerInRight)
        logo.startAnimation(zoomLogo)
        lottieAnimation.startAnimation(fadeInLottie)

        applyTypewriterEffect(sloganText, getString(R.string.eslogan))

        splashScope.launch {
            delay(5000)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            overridePendingTransition(R.anim.slide_fade_in, R.anim.fade_out)
            finish()
        }
    }

    private fun applyTypewriterEffect(textView: TextView, text: String) {
        textView.text = ""
        CoroutineScope(Dispatchers.Main).launch {
            for (char in text) {
                textView.append(char.toString())
                delay(20)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        splashJob.cancel()
    }
}
