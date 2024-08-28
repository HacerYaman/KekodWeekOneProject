package com.example.kekodweekoneproject.ui.main.switchscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.kekodweekoneproject.databinding.FragmentSwitchBinding
import com.example.kekodweekoneproject.domain.usecase.SwitchType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SwitchFragment : Fragment() {

    private var _binding: FragmentSwitchBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<SwitchViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSwitchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.switchState.observe(viewLifecycleOwner) { state ->
            // Update switch states based on the current state
            binding.happiness.isChecked = state.happiness
            binding.optimism.isChecked = state.optimism
            binding.kindness.isChecked = state.kindness
            binding.giving.isChecked = state.giving
            binding.respect.isChecked = state.respect
            binding.ego.isChecked = state.ego
        }

        binding.ego.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onEgoSwitchToggled(isChecked)
        }

        binding.happiness.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onOtherSwitchToggled(SwitchType.Happiness, isChecked)
        }

        binding.optimism.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onOtherSwitchToggled(SwitchType.Optimism, isChecked)
        }

        binding.kindness.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onOtherSwitchToggled(SwitchType.Kindness, isChecked)
        }

        binding.giving.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onOtherSwitchToggled(SwitchType.Giving, isChecked)
        }

        binding.respect.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onOtherSwitchToggled(SwitchType.Respect, isChecked)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
