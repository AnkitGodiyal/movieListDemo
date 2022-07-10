package com.ankit.tmdblistdemo.di

import com.ankit.tmdblistdemo.db.MovieDao
import com.ankit.tmdblistdemo.network.GetData
import com.ankit.tmdblistdemo.repository.MovieRepository
import com.ankit.tmdblistdemo.repository.NetworkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(movieDao: MovieDao): MovieRepository {
        return MovieRepository(movieDao)
    }

    @Singleton
    @Provides
    fun provideNetworkRepository(apiService: GetData): NetworkRepository {
        return NetworkRepository(apiService)
    }
}