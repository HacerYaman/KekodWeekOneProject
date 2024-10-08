package com.example.kekodweekoneproject.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kekodweekoneproject.domain.model.FavoriteQuote

@Database(entities = [FavoriteQuote::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteQuoteDao(): FavoriteQuoteDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}