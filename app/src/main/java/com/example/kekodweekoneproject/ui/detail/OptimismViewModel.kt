package com.example.kekodweekoneproject.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kekodweekoneproject.data.source.local.FavoriteQuoteDao
import com.example.kekodweekoneproject.domain.model.FavoriteQuote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class OptimismViewModel @Inject constructor(
    private val favoriteQuoteDao: FavoriteQuoteDao
) : ViewModel() {


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

    fun addToFavorites(quote: String) {
        viewModelScope.launch {
            favoriteQuoteDao.insert(FavoriteQuote(quote = quote))
        }
    }
}
