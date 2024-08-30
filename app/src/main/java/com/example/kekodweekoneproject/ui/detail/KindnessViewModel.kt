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
class KindnessViewModel @Inject constructor(
    private val favoriteQuoteDao: FavoriteQuoteDao
) : ViewModel() {

    private val quotes = listOf(
        "No act of kindness, no matter how small, is ever wasted.\n— Aesop",
        "Kindness is a language which the deaf can hear and the blind can see.\n— Mark Twain",
        "Wherever there is a human being, there is an opportunity for a kindness.\n— Lucius Annaeus Seneca",
        "A single act of kindness throws out roots in all directions, and the roots spring up and make new trees.\n— Amelia Earhart",
        "Carry out a random act of kindness, with no expectation of reward, safe in the knowledge that one day someone might do the same for you.\n— Princess Diana",
        "Kindness can become its own motive. We are made kind by being kind.\n— Eric Hoffer",
        "You can accomplish by kindness what you cannot by force.\n— Publilius Syrus",
        "The best way to find yourself is to lose yourself in the service of others.\n— Mahatma Gandhi"
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
