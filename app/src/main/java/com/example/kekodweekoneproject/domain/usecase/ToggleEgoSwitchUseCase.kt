package com.example.kekodweekoneproject.domain.usecase

import com.example.kekodweekoneproject.domain.SwitchState
import javax.inject.Inject

class ToggleEgoSwitchUseCase @Inject constructor(

) {
    fun execute(currentState: SwitchState): SwitchState {
        return if (currentState.ego) {
            currentState.copy(
                happiness = false,
                optimism = false,
                kindness = false,
                giving = false,
                respect = false
            )
        } else {
            currentState
        }
    }
}
