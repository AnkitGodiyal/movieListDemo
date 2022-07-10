package com.ankit.tmdblistdemo.network

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.ankit.tmdblistdemo.models.Movie
import io.reactivex.disposables.CompositeDisposable

class UpcomingDataSourceFactory(private val apiService : GetData, private val compositeDisposable: CompositeDisposable)
    : DataSource.Factory<Int, Movie>() {

    val upcomingLiveDataSource =  MutableLiveData<UpcomingDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val upcomingDataSource = UpcomingDataSource(apiService, compositeDisposable)

        upcomingLiveDataSource.postValue(upcomingDataSource)
        return upcomingDataSource
    }

    fun refresh() {
        upcomingLiveDataSource.value?.invalidate()
    }

}