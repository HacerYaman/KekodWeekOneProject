package com.example.kekodweekoneproject.ui.main.switchscreen

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
            view.setBackgroundResource(backgroundResource)

            updateBottomNavigation(state)
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

    private fun updateBottomNavigation(state: SwitchState) {
        val menu = bottomNavigationView.menu
        menu.clear()
        if (state.ego) {
            menu.add(0, R.id.nav_main, 0, R.string.main)
                .setIcon(R.drawable.ic_lotus_empty)
        } else {
            var order = 0
            if (state.happiness) {
                menu.add(0, R.id.happiness, order++, R.string.happiness)
                    .setIcon(R.drawable.ic_happiness)
            }
            if (state.optimism) {
                menu.add(0, R.id.optimism, order++, R.string.optimism)
                    .setIcon(R.drawable.ic_optimism)
            }
            if (state.kindness) {
                menu.add(0, R.id.kindness, order++, R.string.kindness)
                    .setIcon(R.drawable.ic_kindness)
            }
            if (state.giving) {
                menu.add(0, R.id.giving, order++, R.string.giving)
                    .setIcon(R.drawable.ic_giving)
            }
            if (state.respect) {
                menu.add(0, R.id.respect, order++, R.string.respect)
                    .setIcon(R.drawable.respect)
            }
            menu.add(0, R.id.nav_main, order, R.string.main)
                .setIcon(R.drawable.ic_lotus_empty)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
