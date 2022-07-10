package com.ankit.tmdblistdemo.repository

import androidx.lifecycle.LiveData
import com.ankit.tmdblistdemo.db.MovieDao
import com.ankit.tmdblistdemo.models.Movie

class MovieRepository(private val movieDao: MovieDao) {

    fun getCollection(): LiveData<MutableList<Movie>> = movieDao.getCollection()

    suspend fun insertMovie(movie: Movie) {
        movieDao.insertMovie(movie)
    }

    suspend fun deleteMovie(id: Int) {
        movieDao.deleteMovie(id)
    }

}