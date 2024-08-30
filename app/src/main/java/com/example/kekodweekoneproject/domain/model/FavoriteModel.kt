package com.example.kekodweekoneproject.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_quotes")
data class FavoriteQuote(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val quote: String
)