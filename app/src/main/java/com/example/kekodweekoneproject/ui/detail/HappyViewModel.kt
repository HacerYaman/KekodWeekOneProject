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
class HappinessViewModel @Inject constructor(
    private val favoriteQuoteDao: FavoriteQuoteDao
) : ViewModel() {

    private val quotes = listOf(
        "Happiness is not something ready-made. It comes from your own actions.\n— Dalai Lama",
        "The purpose of our lives is to be happy.\n— Dalai Lama",
        "For every minute you are angry you lose sixty seconds of happiness.\n— Ralph Waldo Emerson",
        "Happiness is not a goal; it is a by-product.\n— Eleanor Roosevelt",
        "The most important thing is to enjoy your life—to be happy—it's all that matters.\n— Audrey Hepburn",
        "Happiness depends upon ourselves.\n— Aristotle",
        "Happiness is when what you think, what you say, and what you do are in harmony.\n— Mahatma Gandhi",
        "The only way to find true happiness is to risk being completely cut open.\n— Chuck Palahniuk"
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