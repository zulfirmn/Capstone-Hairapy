package com.android.capstone.hairapy.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.capstone.hairapy.data.adapter.HistoryAdapter
import com.android.capstone.hairapy.databinding.FragmentHistoryBinding

class HistoryFragment: Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private val historyAdapter = HistoryAdapter()

    private val viewModel by activityViewModels<HistoryViewModel> {
        HistoryViewModelFactory.getInstance(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvHistory.adapter = historyAdapter

        binding.rvHistory.apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            adapter = historyAdapter
        }

        viewModel.historyList.observe(viewLifecycleOwner) {
            historyAdapter.submitList(it)
        }
    }
}