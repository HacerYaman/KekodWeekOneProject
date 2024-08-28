package com.example.kekodweekoneproject.domain

import android.view.View.GONE

data class SwitchState(
    val happiness: Boolean = false,
    val optimism: Boolean = false,
    val kindness: Boolean = false,
    val giving: Boolean = false,
    val respect: Boolean = false,
    val ego: Boolean = false,
    val egoTextVisibility: Int= GONE
)