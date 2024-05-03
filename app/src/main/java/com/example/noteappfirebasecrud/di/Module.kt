package com.example.noteappfirebasecrud.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.noteappfirebasecrud.network.NetworkService
import com.example.noteappfirebasecrud.network.impl.NetworkServiceImpl
import com.example.noteappfirebasecrud.storage.Preference
import com.example.noteappfirebasecrud.storage.PreferenceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NoteAppModule{

    @Provides
    @Singleton
    fun providePreferences(pref: SharedPreferences): Preference = PreferenceImpl(pref)

    @Provides
    @Singleton
    fun provideNetworkService(): NetworkService = NetworkServiceImpl()

    @Provides
    @Singleton
    fun provideSharePref(app: Application): SharedPreferences = app.getSharedPreferences("notes_app_shared_pref", MODE_PRIVATE)
}