package dorin_roman.app.kongfujava.di

import android.content.Context
import android.media.MediaPlayer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dorin_roman.app.kongfujava.R
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal object AppSingletonModule {

    @Provides
    @Singleton
    fun provideMusicPlayer(
        @ApplicationContext context: Context
    ): MediaPlayer {
        return MediaPlayer.create(context, R.raw.background_music)
    }

}