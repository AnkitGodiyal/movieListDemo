package com.ankit.tmdblistdemo.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ankit.tmdblistdemo.models.Movie
import com.ankit.tmdblistdemo.network.GetData
import com.ankit.tmdblistdemo.network.MovieDataSourceFactory
import com.ankit.tmdblistdemo.util.POST_PER_PAGE
import io.reactivex.disposables.CompositeDisposable

class NetworkRepository(private val apiService: GetData) {


    private lateinit var moviePagedList: LiveData<PagedList<Movie>>
    private lateinit var moviesDataSourceFactory: MovieDataSourceFactory

    fun fetchLiveMoviePagedList(
        compositeDisposable: CompositeDisposable,
        minYear: String,
        maxYear: String,
        genre: String
    ): LiveData<PagedList<Movie>> {
        moviesDataSourceFactory =
            MovieDataSourceFactory(apiService, compositeDisposable, minYear, maxYear, genre)

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(POST_PER_PAGE)
            .build()

        moviePagedList = LivePagedListBuilder(moviesDataSourceFactory, config).build()

        return moviePagedList
    }

    fun refreshMovies() {
        moviesDataSourceFactory.refresh()
    }

}