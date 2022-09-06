package com.example.firebase_beers.module

import android.content.Context
import androidx.room.Room
import com.example.firebase_beers.api.ApiInterface
import com.example.firebase_beers.api.ApiResources
import com.example.firebase_beers.repo.RepoImpl
import com.example.firebase_beers.repo.RepoInterface
import com.example.firebase_beers.room.BeerDao
import com.example.firebase_beers.room.BeerDatabase
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class Module {
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    fun httpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    fun okHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    @Provides
    fun retrofitBuilder(gson: Gson, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(ApiResources.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

    @Provides
    fun getApiDetails(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }


    @Provides
    fun getRepo(apiInterface: ApiInterface, beerDao: BeerDao): RepoInterface {
        return RepoImpl(apiInterface, beerDao)
    }

    @Provides
    fun provideAcronymDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        BeerDatabase::class.java,
        "AcronymDatabase",
    ).build()

    @Provides
    fun provideDao(database: BeerDatabase) = database.beerDao()

}