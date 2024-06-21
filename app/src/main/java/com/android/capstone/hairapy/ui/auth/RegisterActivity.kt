package com.android.capstone.hairapy.ui.auth

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.android.capstone.hairapy.databinding.ActivityRegisterBinding
import com.android.capstone.hairapy.ui.ViewModelFactory

class RegisterActivity:AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val viewModel by viewModels<RegisterViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerButton.setOnClickListener {
            val username = binding.emailPhoneNumber.text.toString()
            val password = binding.passwordSignUp.text.toString()
            viewModel.userSignUp(username, password)

            viewModel.isLoading.observe(this) {
                binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
            }

            viewModel.isSuccess.observe(this) { isSuccess ->
                if (isSuccess) {
                    viewModel.message.observe(this) { message ->
                        showToast(message)
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                    }
                } else {
                    viewModel.message.observe(this) { message ->
                        showToast(message)
                    }
                }
            }
        }

        setupObservers()
        setupView()
    }

    private fun setupObservers() {
        viewModel.isLoading.observe(this) {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModel.isSuccess.observe(this) { isSuccess ->
            if (isSuccess) {
                viewModel.message.observe(this) { message ->
                    showToast(message)
                }
                navigateToLogin()
            } else {
                viewModel.message.observe(this) { message ->
                    showToast(message)
                }
            }
        }
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
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