package com.android.capstone.hairapy.ui.camera.result

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.android.capstone.hairapy.ui.main.MainActivity
import com.android.capstone.hairapy.databinding.FragmentRecommendationBinding
import com.bumptech.glide.Glide

class RecommendationFragment: Fragment() {

    private lateinit var binding:FragmentRecommendationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecommendationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: RecommendationFragmentArgs by navArgs()
        val recommendation = args.recProduct

        binding.apply {
            nameProduct.text = recommendation.name
            Glide.with(requireActivity())
                .load(recommendation.image)
                .into(imgProduct)
        }

        binding.btnBackToHome.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(intent)
        }
    }
}