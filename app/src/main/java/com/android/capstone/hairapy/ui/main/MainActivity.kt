package com.android.capstone.hairapy.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.android.capstone.hairapy.R
import com.android.capstone.hairapy.databinding.ActivityMainBinding
import com.android.capstone.hairapy.ui.ViewModelFactory
import com.android.capstone.hairapy.ui.camera.ContinueCameraFragment
import com.android.capstone.hairapy.ui.history.HistoryFragment
import com.android.capstone.hairapy.ui.splash.OnboardingActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getSession().observe(this) { user ->
            if (!user.isLogin) {
                startActivity(Intent(this, OnboardingActivity::class.java))
                finish()
            }
        }

        binding.bottomNavigationView.background = null

        // Set the initial fragment
        if (savedInstanceState == null) {
            replaceFragment(MainFragment())
        }

        @Suppress("DEPRECATION")
        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    replaceFragment(MainFragment())
                    true
                }
                R.id.camera -> {
                    replaceFragment(ContinueCameraFragment())
                    true
                }
                R.id.history -> {
                    replaceFragment(HistoryFragment())
                    true
                }

                else -> false
            }
        }

        //     binding.fab.setOnClickListener {
        // Handle FAB click, e.g., open camera or navigate to a specific fragment
        // For example, open a new fragment or activity
        //    }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, fragment)
            .commit()
    }
}