package com.example.kekodweekoneproject.ui.main.switchscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kekodweekoneproject.R
import com.example.kekodweekoneproject.domain.SwitchState
import com.example.kekodweekoneproject.domain.usecase.SwitchType
import com.example.kekodweekoneproject.domain.usecase.ToggleEgoSwitchUseCase
import com.example.kekodweekoneproject.domain.usecase.ToggleOtherSwitchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SwitchViewModel @Inject constructor(
    private val toggleEgoSwitchUseCase: ToggleEgoSwitchUseCase,
    private val toggleOtherSwitchUseCase: ToggleOtherSwitchUseCase
) : ViewModel() {

    private val _switchState = MutableLiveData(SwitchState())
    val switchState: LiveData<SwitchState> get() = _switchState

    fun onEgoSwitchToggled(isOn: Boolean) {
        val newState = toggleEgoSwitchUseCase.execute(_switchState.value!!.copy(ego = isOn, egoText = R.string.ego_kills_everything))
        _switchState.value = newState
    }

    fun onOtherSwitchToggled(switchType: SwitchType, isOn: Boolean) {
        val newState = toggleOtherSwitchUseCase.execute(_switchState.value!!, switchType, isOn)
        _switchState.value = newState
    }
}