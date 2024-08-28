package com.example.kekodweekoneproject.domain.usecase

import com.example.kekodweekoneproject.R
import com.example.kekodweekoneproject.domain.SwitchState
import javax.inject.Inject

class ToggleOtherSwitchUseCase  @Inject constructor() {
    fun execute(currentState: SwitchState, switchType: SwitchType, isOn: Boolean): SwitchState {
        return if (!currentState.ego) {
            currentState.copy(egoText = R.string.balance)
            when (switchType) {
                SwitchType.Happiness -> currentState.copy(happiness = isOn)
                SwitchType.Optimism -> currentState.copy(optimism = isOn)
                SwitchType.Kindness -> currentState.copy(kindness = isOn)
                SwitchType.Giving -> currentState.copy(giving = isOn)
                SwitchType.Respect -> currentState.copy(respect = isOn)
            }
        } else {
            currentState
        }
    }
}


enum class SwitchType {
    Happiness, Optimism, Kindness, Giving, Respect
}
