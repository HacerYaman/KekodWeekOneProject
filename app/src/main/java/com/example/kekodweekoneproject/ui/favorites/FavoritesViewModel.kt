package com.example.kekodweekoneproject.ui.favorites


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kekodweekoneproject.data.source.local.AppDatabase
import com.example.kekodweekoneproject.domain.model.FavoriteQuote
import kotlinx.coroutines.launch

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {

    private val database = AppDatabase.getDatabase(application)
    private val favoriteQuoteDao = database.favoriteQuoteDao()

    private val _favoriteQuotes = MutableLiveData<List<FavoriteQuote>>()
    val favoriteQuotes: LiveData<List<FavoriteQuote>> get() = _favoriteQuotes

    init {
        loadFavorites()
    }

    private fun loadFavorites() {
        viewModelScope.launch {
            _favoriteQuotes.value = favoriteQuoteDao.getAllFavorites()
        }
    }

    fun deleteFavoriteQuote(id: Int) {
        viewModelScope.launch {
            favoriteQuoteDao.deleteById(id)
        }
    }
}
