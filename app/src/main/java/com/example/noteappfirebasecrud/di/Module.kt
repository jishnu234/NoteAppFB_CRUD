package com.example.noteappfirebasecrud.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.noteappfirebasecrud.storage.Preference
import com.example.noteappfirebasecrud.storage.PreferenceImpl
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
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
    fun provideFirebaseCollection(): CollectionReference = Firebase.firestore.collection("notes_collection")

    @Provides
    @Singleton
    fun provideSharePref(app: Application): SharedPreferences = app.getSharedPreferences("notes_app_shared_pref", MODE_PRIVATE)
}