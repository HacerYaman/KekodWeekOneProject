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
class RespectViewModel @Inject constructor(
    private val favoriteQuoteDao: FavoriteQuoteDao
) : ViewModel() {


    private val quotes = listOf(
        "Respect yourself and others will respect you.\n— Confucius",
        "One of the most sincere forms of respect is actually listening to what another has to say.\n— Bryant H. McGill",
        "Respect for ourselves guides our morals; respect for others guides our manners.\n— Laurence Sterne",
        "We must learn to live together as brothers or perish together as fools.\n— Martin Luther King Jr.",
        "Respect is earned, not given.\n— Unknown",
        "The right to be respected is won by respecting others.\n— Vasily Grossman",
        "Without feelings of respect, what is there to distinguish men from beasts?\n— Confucius",
        "Mutual respect is the foundation of genuine harmony.\n— Dalai Lama"
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
