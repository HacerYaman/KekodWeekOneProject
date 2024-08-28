package com.example.kekodweekoneproject.ui.main.switchscreen

import android.app.AlertDialog
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

            binding.egoKillsTw.text = if (state.ego) {
                getString(R.string.ego_kills_everything)
            } else {
                getString(R.string.balance)
            }

            applyBackgroundTransition(view, state)

            viewModel.updateBottomNavigation(bottomNavigationView.menu)
        }

        viewModel.showPopup.observe(viewLifecycleOwner) { showPopup ->
            if (showPopup) {
                showPopupDialog()
            }
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
            else -> R.drawable.gradient_background
        }

        val transitionDrawable = TransitionDrawable(
            arrayOf(
                view.background,
                requireContext().getDrawable(backgroundResource)
            )
        )
        view.background = transitionDrawable
        transitionDrawable.startTransition(500)
    }

    private fun showPopupDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Nothing is perfect!")
            .setMessage("The number of active switches cannot exceed 5.")
            .setPositiveButton(android.R.string.ok, null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}