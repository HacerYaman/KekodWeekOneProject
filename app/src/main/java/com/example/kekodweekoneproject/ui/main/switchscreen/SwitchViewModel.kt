package com.example.kekodweekoneproject.ui.main.switchscreen

import android.view.Menu
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kekodweekoneproject.R
import com.example.kekodweekoneproject.domain.SwitchState
import com.example.kekodweekoneproject.domain.usecase.SwitchType
import com.example.kekodweekoneproject.domain.usecase.ToggleEgoSwitchUseCase
import com.example.kekodweekoneproject.domain.usecase.ToggleOtherSwitchUseCase
import com.example.kekodweekoneproject.domain.usecase.UpdateBottomNavUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SwitchViewModel @Inject constructor(
    private val updateBottomNavUseCase: UpdateBottomNavUseCase,
    private val toggleEgoSwitchUseCase: ToggleEgoSwitchUseCase,
    private val toggleOtherSwitchUseCase: ToggleOtherSwitchUseCase,
) : ViewModel() {

    private val _switchState = MutableLiveData(SwitchState())
    val switchState: LiveData<SwitchState> get() = _switchState

    private val _showPopup = MutableLiveData<Boolean>()
    val showPopup: LiveData<Boolean> get() = _showPopup

    fun onEgoSwitchToggled(isOn: Boolean) {
        val newState = toggleEgoSwitchUseCase.execute(_switchState.value!!.copy(ego = isOn, egoText = R.string.ego_kills_everything))
        _switchState.value = newState
    }

    fun onOtherSwitchToggled(switchType: SwitchType, isOn: Boolean) {
        val newState = toggleOtherSwitchUseCase.execute(_switchState.value!!, switchType, isOn)
        _switchState.value = newState
    }

    fun updateBottomNavigation(menu: Menu) {
        _switchState.value?.let { state ->
            updateBottomNavUseCase.execute(menu, state)
        }
    }

    private fun checkActiveCount(state: SwitchState) {
        _showPopup.value = state.activeCount > 5
    }

//    val backgroundResource: LiveData<Int> = Transformations.map(_switchState) { state ->
//        getBackgroundResourceUseCase.execute(state)
//    }
}