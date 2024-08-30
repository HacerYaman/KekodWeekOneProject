package com.example.kekodweekoneproject.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.kekodweekoneproject.domain.model.FavoriteQuote

@Dao
interface FavoriteQuoteDao {
    @Insert
    suspend fun insert(quote: FavoriteQuote)

    @Query("SELECT * FROM favorite_quotes")
    suspend fun getAllFavorites(): List<FavoriteQuote>

    @Query("DELETE FROM favorite_quotes WHERE id = :id")
    suspend fun deleteById(id: Int)
}