package com.android.capstone.hairapy.ui.camera.result

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.android.capstone.hairapy.R
import com.android.capstone.hairapy.data.db.HistoryPrediction
import com.android.capstone.hairapy.data.utils.convertMillisToDateString
import com.android.capstone.hairapy.data.utils.toFile
import com.android.capstone.hairapy.databinding.FragmentResultBinding
import com.android.capstone.hairapy.ui.ViewModelFactory
import com.android.capstone.hairapy.ui.history.HistoryViewModel
import com.android.capstone.hairapy.ui.history.HistoryViewModelFactory
import com.bumptech.glide.Glide
import java.io.File
import java.io.FileOutputStream

class ResultFragment: Fragment() {

    private lateinit var binding: FragmentResultBinding
    private val navsArgs by navArgs<ResultFragmentArgs>()

    private val viewModel by activityViewModels<ResultViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }
    private val historyViewModel by activityViewModels<HistoryViewModel> {
        HistoryViewModelFactory.getInstance(requireActivity().application)
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
            val file = imgResult.imageUri?.toFile(requireActivity())
            file
        }

        if (imageFile != null) {
            viewModel.uploadImage(imageFile)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModel.result.observe(viewLifecycleOwner) {
            val hair = it.substringBeforeLast(" ")

            val tips = when (hair) {
                "Hair Loss" -> resources.getStringArray(R.array.hair_loss)
                "Dandruff" -> resources.getStringArray(R.array.dandruff)
                "Psoriasis" -> resources.getStringArray(R.array.psoriasis)
                "Hair Greasy" -> resources.getStringArray(R.array.hair_greasy)
                else -> arrayOf("No Tips Available")
            }

            val tipsText = tips.joinToString(separator = "\n") { "- $it" }

            binding.apply {
                tvDisease.text = it
                textView11.text = getString(R.string.todo, hair)
                tvSolution.text = tipsText
            }

            val historyPredict = HistoryPrediction(
                result = it,
                uri = imgResult.imageUri.toString(),
                date = convertMillisToDateString(System.currentTimeMillis())
            )

            historyViewModel.addHistory(historyPredict)
        }

        binding.apply {
            Glide.with(requireActivity())
                .load(if (imgResult.isFromCamera) imgResult.imageBitmap else imgResult.imageUri)
                .into(ivStory)

            btnToRecommendation.setOnClickListener {
                viewModel.recProduct.observe(viewLifecycleOwner) { data ->
                    val intent = ResultFragmentDirections.actionResultFragmentToRecommendationFragment(data)
                    findNavController().navigate(intent)
                }
            }
        }
    }
}