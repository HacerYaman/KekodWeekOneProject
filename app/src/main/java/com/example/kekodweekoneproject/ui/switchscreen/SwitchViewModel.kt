package com.example.kekodweekoneproject.ui.switchscreen

import android.view.Menu
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kekodweekoneproject.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SwitchViewModel @Inject constructor(
) : ViewModel() {

    private val _switchState = MutableLiveData(SwitchState())
    val switchState: LiveData<SwitchState> get() = _switchState

    private val _showPopup = MutableLiveData<Boolean>()
    val showPopup: LiveData<Boolean> get() = _showPopup

    private val _isBottomNavVisible = MutableLiveData<Boolean>()
    val isBottomNavVisible: LiveData<Boolean> get() = _isBottomNavVisible

    fun onEgoSwitchToggled(isOn: Boolean) {
        val newState = execute(_switchState.value!!.copy(ego = isOn))
        _switchState.value = newState

        _isBottomNavVisible.value = !isOn
    }

    fun onOtherSwitchToggled(switchType: SwitchType, isOn: Boolean) {
        val newState = executeOtherSwitch(_switchState.value!!, switchType, isOn)
        _switchState.value = newState
    }

    fun updateBottomNavigation(menu: Menu) {
        _switchState.value?.let { state ->
            execute(menu, state)
        }
    }

    private fun execute(currentState: SwitchState): SwitchState {
        return if (currentState.ego) {
            currentState.copy(
                happiness = false,
                optimism = false,
                kindness = false,
                giving = false,
                respect = false,
                egoText = R.string.ego_kills_everything
            )
        } else {
            currentState.copy(
                egoText = R.string.balance
            )
        }
    }

    private fun executeOtherSwitch(currentState: SwitchState, switchType: SwitchType, isOn: Boolean): SwitchState {
        return if (!currentState.ego) {
            when (switchType) {
                SwitchType.Happiness -> currentState.copy(happiness = isOn)
                SwitchType.Optimism -> currentState.copy(optimism = isOn)
                SwitchType.Kindness -> currentState.copy(kindness = isOn)
                SwitchType.Giving -> currentState.copy(giving = isOn)
                SwitchType.Respect -> currentState.copy(respect = isOn)
            }.copy(egoText = R.string.balance)
        } else {
            currentState
        }
    }

    enum class SwitchType {
        Happiness, Optimism, Kindness, Giving, Respect
    }

    private fun execute(menu: Menu, state: SwitchState) {
        menu.clear()

        var order = 1
        var addedCount = 0

        if (state.ego) {
            menu.add(0, R.id.nav_main, 0, R.string.main)
                .setIcon(R.drawable.ic_lotus_empty)
        } else {
            if (state.happiness && addedCount < 4) {
                menu.add(0, R.id.happiness, order++, R.string.happiness)
                    .setIcon(R.drawable.ic_happiness)
                addedCount++
            }
            if (state.optimism && addedCount < 4) {
                menu.add(0, R.id.optimism, order++, R.string.optimism)
                    .setIcon(R.drawable.ic_optimism)
                addedCount++
            }
            if (state.kindness && addedCount < 4) {
                menu.add(0, R.id.kindness, order++, R.string.kindness)
                    .setIcon(R.drawable.ic_kindness)
                addedCount++
            }
            if (state.giving && addedCount < 4) {
                menu.add(0, R.id.giving, order++, R.string.giving)
                    .setIcon(R.drawable.ic_giving)
                addedCount++
            }
            if (state.respect && addedCount < 4) {
                menu.add(0, R.id.respect, order++, R.string.respect)
                    .setIcon(R.drawable.respecttt)
                addedCount++
            }
            menu.add(0, R.id.nav_main, 0, R.string.main)
                .setIcon(R.drawable.ic_lotus_empty)
        }
    }
}
