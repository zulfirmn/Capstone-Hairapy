package com.android.capstone.hairapy.ui.auth

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.android.capstone.hairapy.ui.main.MainActivity
import com.android.capstone.hairapy.databinding.ActivityLoginBinding
import com.android.capstone.hairapy.ui.ViewModelFactory

class LoginActivity:AppCompatActivity() {

    private lateinit var binding:ActivityLoginBinding
    private val viewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


            binding.signInButton.setOnClickListener {
//                val username = binding.emailPhoneNumber.text.toString()
//                val password = binding.confirmPassword.text.toString()
//                viewModel.userLogin(username, password)
//
//                viewModel.isLoading.observe(this) {
//                    showLoading(it)
//                }
//
//                viewModel.isSuccess.observe(this) {
//                    alert(it)
//                }
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

            binding.signUpLink.setOnClickListener {
                val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                startActivity(intent)
                finish()
            }

        setupView()

    }

    private fun alert(isSuccess: Boolean) {
        viewModel.message.observe(this) { message ->
            if (isSuccess) {
                AlertDialog.Builder(this).apply {
                    setTitle("Berhasil")
                    setMessage(message)
                    setPositiveButton("Lanjut") { _, _ ->
                        val intent = Intent(context, MainActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        }
                        startActivity(intent)
                    }
                    create()
                    show()
                }
            } else {
                AlertDialog.Builder(this).apply {
                    setTitle("Gagal")
                    setMessage(message)
                    setPositiveButton("Tutup") { dialog, _ ->
                        dialog.cancel()
                    }
                    create()
                    show()
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
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