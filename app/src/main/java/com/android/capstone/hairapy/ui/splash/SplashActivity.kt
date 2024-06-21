package com.android.capstone.hairapy.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.android.capstone.hairapy.R
import com.android.capstone.hairapy.ui.main.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity:AppCompatActivity() {

    private var mDelayHandler: Handler? = null
    private var durasi: Long=1500 //1.5 detik

    private val mRunnable: Runnable = Runnable {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()

        mDelayHandler = Handler()

        mDelayHandler!!.postDelayed(mRunnable,durasi)

        setupView()

    }

    private fun setupView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            this.window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            this.window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }

}