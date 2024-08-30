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
class GivingViewModel @Inject constructor(
    private val favoriteQuoteDao: FavoriteQuoteDao
) : ViewModel() {

    private val quotes = listOf(
        "The best way to find yourself is to lose yourself in the service of others.\n— Mahatma Gandhi",
        "No one has ever become poor by giving.\n— Anne Frank",
        "We make a living by what we get, but we make a life by what we give.\n— Winston Churchill",
        "The meaning of life is to find your gift. The purpose of life is to give it away.\n— Pablo Picasso",
        "The joy of giving is the most precious joy in life.\n— Unknown",
        "Giving is not just about making a donation. It is about making a difference.\n— Kathy Calvin",
        "To give without any reward, or any notice, has a special quality of its own.\n— Anne Morrow Lindbergh",
        "You give but little when you give of your possessions. It is when you give of yourself that you truly give.\n— Khalil Gibran"
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
