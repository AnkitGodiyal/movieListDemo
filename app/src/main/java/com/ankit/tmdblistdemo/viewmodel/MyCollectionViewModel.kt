package com.ankit.tmdblistdemo.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ankit.tmdblistdemo.models.Movie
import com.ankit.tmdblistdemo.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyCollectionViewModel @Inject constructor(
    application: Application,
    private val repository: MovieRepository
) : AndroidViewModel(application) {

    var myMovieList: LiveData<MutableList<Movie>>

    init {
        myMovieList = repository.getCollection()
    }

    fun insert(movie: Movie) {
        viewModelScope.launch {
            repository.insertMovie(movie)
        }

    }

    fun deleteMovie(movieId: Int) {
        viewModelScope.launch {
            repository.deleteMovie(movieId)
        }
    }
}