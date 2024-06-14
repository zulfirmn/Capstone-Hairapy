package com.android.capstone.hairapy.ui.camera

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.capstone.hairapy.R
import com.android.capstone.hairapy.databinding.FragmentContinueCameraBinding
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView

class ContinueCameraFragment: Fragment() {

    private lateinit var binding: FragmentContinueCameraBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContinueCameraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            btnToCamera.setOnClickListener {
                val intent = Intent(activity, DetectActivity::class.java)
                startActivity(intent)
            }

        }

        setupView()

    }

    private fun setupView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            requireActivity().window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            requireActivity().window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }

    override fun onResume() {
        super.onResume()
        hideBottomNavigationView()

    }

    override fun onPause() {
        super.onPause()
        showBottomNavigationView()
    }

    private fun hideBottomNavigationView() {
        val activity = requireActivity()
        val bottomNavigationView = activity.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val bottomAppBar = activity.findViewById<BottomAppBar>(R.id.bottomAppBar)

        bottomNavigationView.visibility = View.GONE
        bottomAppBar.visibility = View.GONE
    }

    private fun showBottomNavigationView() {
        val activity = requireActivity()
        val bottomNavigationView = activity.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val bottomAppBar = activity.findViewById<BottomAppBar>(R.id.bottomAppBar)

        bottomNavigationView.visibility = View.VISIBLE
        bottomAppBar.visibility = View.VISIBLE
    }



}