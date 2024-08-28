package com.example.kekodweekoneproject.domain.usecase

import android.view.View.GONE
import android.view.View.VISIBLE
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
                respect = false,
                egoTextVisibility = VISIBLE,
            )
        } else {
            currentState.copy(
                egoTextVisibility = GONE
            )
        }
    }
}
