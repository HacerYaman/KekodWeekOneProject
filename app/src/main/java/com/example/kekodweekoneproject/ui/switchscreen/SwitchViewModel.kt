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
    
    fun onEgoSwitchToggled(isOn: Boolean) {
        val newState = execute(_switchState.value!!.copy(ego = isOn))
        _switchState.value = newState
    }

    fun onOtherSwitchToggled(switchType: SwitchType, isOn: Boolean, menu: Menu) {
        val currentActiveCount = _switchState.value!!.activeCount
        if (currentActiveCount < 4 || !isOn) {
            val newState = executeOtherSwitch(_switchState.value!!, switchType, isOn)
            _switchState.value = newState
            updateBottomNavigation(menu)
        } else {
            showMaxItemsReachedMessage()
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


    private fun updateBottomNavigation(menu: Menu) {
        menu.clear()

        val state = _switchState.value!!

        menu.add(0, R.id.switchFragment, 0, R.string.state)
            .setIcon(R.drawable.ic_lotus_empty)

        var order = 1

        if (state.happiness) {
            menu.add(0, R.id.happyFragment, order++, R.string.happiness)
                .setIcon(R.drawable.ic_happiness)
        }
        if (state.optimism) {
            menu.add(0, R.id.optimismFragment, order++, R.string.optimism)
                .setIcon(R.drawable.ic_optimism)
        }
        if (state.kindness) {
            menu.add(0, R.id.kindnessFragment, order++, R.string.kindness)
                .setIcon(R.drawable.ic_kindness)
        }
        if (state.giving) {
            menu.add(0, R.id.givingFragment, order++, R.string.giving)
                .setIcon(R.drawable.ic_giving)
        }
        if (state.respect) {
            menu.add(0, R.id.respectFragment, order++, R.string.respect)
                .setIcon(R.drawable.respecttt)
        }
    }

    private fun showMaxItemsReachedMessage() {
        print("asdasdas")
    }

}
