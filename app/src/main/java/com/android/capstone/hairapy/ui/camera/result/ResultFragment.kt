package com.android.capstone.hairapy.ui.camera.result

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.android.capstone.hairapy.databinding.FragmentResultBinding
import com.android.capstone.hairapy.ui.ViewModelFactory
import com.android.capstone.hairapy.ui.camera.CameraFragmentDirections
import com.bumptech.glide.Glide
import java.io.File
import java.io.FileOutputStream

class ResultFragment: Fragment() {

    private lateinit var binding: FragmentResultBinding
    private val navsArgs by navArgs<ResultFragmentArgs>()
    private val viewModel by activityViewModels<ResultViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

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
        val imgResult = navsArgs.imageResult

        val imageFile = if (imgResult.isFromCamera) {
            // Convert Bitmap to File
            val file = File(requireContext().cacheDir, "image.jpg")
            val out = FileOutputStream(file)
            imgResult.imageBitmap?.compress(Bitmap.CompressFormat.JPEG, 100, out)
            out.flush()
            out.close()
            file
        } else {
            // Get File from Uri
            val uri = imgResult.imageUri
            val filePath = uri?.path
            filePath?.let { File(it) }
        }

        if (imageFile != null) {
            viewModel.uploadImage(imageFile)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModel.result.observe(viewLifecycleOwner) {
            binding.tvDisease.text = it
        }

        binding.apply {
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