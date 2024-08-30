package com.example.kekodweekoneproject.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class OptimismViewModel : ViewModel() {

    private val quotes = listOf(
        "Keep your face always toward the sunshine—and shadows will fall behind you.\n— Walt Whitman",
        "The only limit to our realization of tomorrow is our doubts of today.\n— Franklin D. Roosevelt",
        "Optimism is the faith that leads to achievement. Nothing can be done without hope and confidence.\n— Helen Keller",
        "Pessimism leads to weakness, optimism to power.\n— William James",
        "A positive attitude gives you power over your circumstances instead of your circumstances having power over you.\n— Joyce Meyer",
        "The pessimist sees difficulty in every opportunity. The optimist sees opportunity in every difficulty.\n— Winston Churchill",
        "Don’t let yesterday take up too much of today.\n— Will Rogers",
        "Believe you can and you’re halfway there.\n— Theodore Roosevelt"
    )

    private val _randomQuote = MutableLiveData<String>()
    val randomQuote: LiveData<String> get() = _randomQuote

    fun getRandomQuote() {
        _randomQuote.value = quotes[Random.nextInt(quotes.size)]
    }
}
