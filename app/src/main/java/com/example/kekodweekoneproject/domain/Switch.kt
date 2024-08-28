package com.example.kekodweekoneproject.domain

import android.support.annotation.StringRes
import com.example.kekodweekoneproject.R

data class SwitchState(
    val happiness: Boolean = false,
    val optimism: Boolean = false,
    val kindness: Boolean = false,
    val giving: Boolean = false,
    val respect: Boolean = false,
    val ego: Boolean = true,
    @StringRes val egoText: Int = R.string.ego_kills_everything
)