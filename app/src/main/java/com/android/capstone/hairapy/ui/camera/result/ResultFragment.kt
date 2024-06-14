package com.android.capstone.hairapy.ui.camera.result

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.android.capstone.hairapy.databinding.FragmentResultBinding
import com.android.capstone.hairapy.ui.camera.CameraFragmentDirections
import com.bumptech.glide.Glide

class ResultFragment: Fragment() {

    private lateinit var binding: FragmentResultBinding
    private val navsArgs by navArgs<ResultFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setsUpView()

    }

    private fun setsUpView(){

        binding.apply {

            val imgResult = navsArgs.imageResult
            Glide.with(requireActivity())
                .load(if (imgResult.isFromCamera) imgResult.imageBitmap else imgResult.imageUri)
                .into(ivStory)

            btnToRecommendation.setOnClickListener {
                val intent = ResultFragmentDirections.actionResultFragmentToRecommendationFragment()
                findNavController().navigate(intent)
            }

        }

    }




}