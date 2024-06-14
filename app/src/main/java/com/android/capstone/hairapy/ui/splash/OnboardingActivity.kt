package com.android.capstone.hairapy.ui.splash

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.android.capstone.hairapy.databinding.ActivityOnboardingBinding
import com.android.capstone.hairapy.ui.auth.LoginActivity
import com.android.capstone.hairapy.ui.auth.RegisterActivity

class OnboardingActivity:AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            btnSignupOnboard.setOnClickListener {
                val intent = Intent(this@OnboardingActivity, RegisterActivity::class.java)
                startActivity(intent)
            }

            tvToLoginOnboard.setOnClickListener {
                val intent = Intent(this@OnboardingActivity, LoginActivity::class.java)
                startActivity(intent)
            }

        }

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