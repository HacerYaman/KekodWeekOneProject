package com.example.kekodweekoneproject.ui.main.switchscreen

import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.kekodweekoneproject.R
import com.example.kekodweekoneproject.databinding.FragmentSwitchBinding
import com.example.kekodweekoneproject.domain.SwitchState
import com.example.kekodweekoneproject.domain.usecase.SwitchType
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SwitchFragment : Fragment() {

    private var _binding: FragmentSwitchBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<SwitchViewModel>()
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSwitchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottomNavigationView = requireActivity().findViewById(R.id.bottomNav)

        viewModel.switchState.observe(viewLifecycleOwner) { state ->
            binding.happiness.isChecked = state.happiness
            binding.optimism.isChecked = state.optimism
            binding.kindness.isChecked = state.kindness
            binding.giving.isChecked = state.giving
            binding.respect.isChecked = state.respect
            binding.ego.isChecked = state.ego

            if (state.ego) {
                binding.egoKillsTw.text = getString(R.string.ego_kills_everything)
            } else {
                binding.egoKillsTw.text = getString(R.string.balance)
            }

            val backgroundResource = if (state.ego) {
                R.drawable.gradient_background_ego
            } else {
                R.drawable.gradient_background
            }

            applyBackgroundTransition(view, state)

            viewModel.updateBottomNavigation(bottomNavigationView.menu)
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

    private fun applyBackgroundTransition(view: View, state: SwitchState) {
        val backgroundResource = when {
            state.ego -> R.drawable.gradient_background_ego
            state.activeCount == 1 -> R.drawable.gradient_green_one
            state.activeCount == 2 -> R.drawable.gradient_green_two
            state.activeCount == 3 -> R.drawable.gradient_green_three
            state.activeCount == 4 -> R.drawable.gradient_green_four
            state.activeCount == 5 -> R.drawable.gradient_green_five
            else -> R.drawable.gradient_background // default background
        }

        // Create TransitionDrawable with two layers: current and new background
        val transitionDrawable = TransitionDrawable(
            arrayOf(
                view.background, // Current background
                requireContext().getDrawable(backgroundResource)
            )
        )

        // Set TransitionDrawable as the background
        view.background = transitionDrawable

        // Start the transition
        transitionDrawable.startTransition(500) // Transition duration in milliseconds
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}