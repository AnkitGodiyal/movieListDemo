package com.ankit.tmdblistdemo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ankit.tmdblistdemo.models.GenreString
import com.ankit.tmdblistdemo.models.Movie

@Database(entities = arrayOf(Movie::class, GenreString::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

}