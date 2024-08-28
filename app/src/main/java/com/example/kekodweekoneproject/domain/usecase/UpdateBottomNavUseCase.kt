package com.example.kekodweekoneproject.domain.usecase

import android.view.Menu
import com.example.kekodweekoneproject.R
import com.example.kekodweekoneproject.domain.SwitchState
import javax.inject.Inject

class UpdateBottomNavUseCase @Inject constructor() {

    fun execute(menu: Menu, state: SwitchState) {
        menu.clear()

        if (state.ego) {
            menu.add(0, R.id.nav_main, 0, R.string.main)
                .setIcon(R.drawable.ic_lotus_empty)
        } else {
            var order = 0
            var addedCount = 0

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

            menu.add(0, R.id.nav_main, order, R.string.main)
                .setIcon(R.drawable.ic_lotus_empty)
        }
    }
}
