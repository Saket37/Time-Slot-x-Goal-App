package com.example.timeslotxgoalapp.di

import android.content.Context
import com.example.timeslotxgoalapp.helper.PlayCompletedAudio
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MediaPlayerModule {
    @Singleton
    @Provides
    fun providesMediaPlayer(@ApplicationContext context: Context): PlayCompletedAudio {
        return PlayCompletedAudio(context)
    }
}