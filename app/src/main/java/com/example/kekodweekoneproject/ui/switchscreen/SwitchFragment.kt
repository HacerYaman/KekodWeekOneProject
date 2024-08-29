package com.example.kekodweekoneproject.ui.switchscreen

import android.app.AlertDialog
import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.example.kekodweekoneproject.R
import com.example.kekodweekoneproject.databinding.FragmentSwitchBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SwitchFragment : Fragment() {

    private var _binding: FragmentSwitchBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<SwitchViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSwitchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.switchState.observe(viewLifecycleOwner) { state ->
            binding.happiness.isChecked = state.happiness
            binding.optimism.isChecked = state.optimism
            binding.kindness.isChecked = state.kindness
            binding.giving.isChecked = state.giving
            binding.respect.isChecked = state.respect
            binding.ego.isChecked = state.ego

            binding.egoKillsTw.text = getString(state.egoText)

            applyBackgroundTransition(view, state)
            toggleBottomNavigationView(state.ego)
        }

        binding.ego.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onEgoSwitchToggled(isChecked)
        }

        binding.happiness.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onOtherSwitchToggled(SwitchViewModel.SwitchType.Happiness, isChecked)
        }

        binding.optimism.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onOtherSwitchToggled(SwitchViewModel.SwitchType.Optimism, isChecked)
        }

        binding.kindness.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onOtherSwitchToggled(SwitchViewModel.SwitchType.Kindness, isChecked)
        }

        binding.giving.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onOtherSwitchToggled(SwitchViewModel.SwitchType.Giving, isChecked)
        }

        binding.respect.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onOtherSwitchToggled(SwitchViewModel.SwitchType.Respect, isChecked)
        }
    }

    private fun toggleBottomNavigationView(isEgoOn: Boolean) {
        val activity = requireActivity() as AppCompatActivity
        val bottomNavigationView = activity.findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNavigationView.visibility = if (isEgoOn) View.GONE else View.VISIBLE
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