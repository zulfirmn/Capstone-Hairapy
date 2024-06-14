package com.android.capstone.hairapy.ui.camera.result

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.capstone.hairapy.MainActivity
import com.android.capstone.hairapy.databinding.ActivityRecommendationBinding

class RecommendationActivity:AppCompatActivity() {

    private lateinit var binding: ActivityRecommendationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecommendationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBackToHome.setOnClickListener {
            val intent = Intent(this@RecommendationActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

}