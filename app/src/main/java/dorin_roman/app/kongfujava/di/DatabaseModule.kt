package dorin_roman.app.kongfujava.di

import android.app.Application
import android.content.Context
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dorin_roman.app.kongfujava.data.repository.AuthRepositoryImpl
import dorin_roman.app.kongfujava.data.repository.CodesRepositoryImpl
import dorin_roman.app.kongfujava.data.repository.LinkedAccountsRepositoryImpl
import dorin_roman.app.kongfujava.data.repository.UsersRepositoryImpl
import dorin_roman.app.kongfujava.di.provider.CodeProvider
import dorin_roman.app.kongfujava.di.provider.IdProvider
import dorin_roman.app.kongfujava.di.provider.KongFuDatabaseProvider
import dorin_roman.app.kongfujava.domain.repository.AuthRepository
import dorin_roman.app.kongfujava.domain.repository.CodeRepository
import dorin_roman.app.kongfujava.domain.repository.LinkedAccountsRepository
import dorin_roman.app.kongfujava.domain.repository.UsersRepository
import dorin_roman.app.kongfujava.domain.source.KongFuDataBase
import dorin_roman.app.kongfujava.domain.source.LevelDao
import dorin_roman.app.kongfujava.domain.source.WorldDao


@Module
@InstallIn(ViewModelComponent::class)
object DatabaseModule {

    @Provides
    fun provideAuthRepository(): AuthRepository = AuthRepositoryImpl(
        auth = Firebase.auth
    )

    @Provides
    fun provideUsersRepository(): UsersRepository = UsersRepositoryImpl(
        database = FirebaseDatabase.getInstance()
    )

    @Provides
    fun provideCodesRepository(): CodeRepository = CodesRepositoryImpl(
        database = FirebaseDatabase.getInstance()
    )

    @Provides
    fun provideLinkedAccountsRepository(): LinkedAccountsRepository = LinkedAccountsRepositoryImpl(
        database = FirebaseDatabase.getInstance()
    )

    @Provides
    fun provideCode(): CodeProvider = CodeProvider

    @Provides
    fun provideId(): IdProvider = IdProvider

    @Provides
    fun provideVideoPlayer(app: Application) : Player {
        return ExoPlayer.Builder(app)
            .build()
    }

    @Provides
    fun provideDataBase(
        @ApplicationContext context: Context
    ): KongFuDataBase = KongFuDatabaseProvider.provide(context)

    @Provides
    fun provideWorldDao(
        dataBase: KongFuDataBase
    ): WorldDao = dataBase.worldDao()

    @Provides
    fun provideLevelDao(
        dataBase: KongFuDataBase
    ): LevelDao = dataBase.levelDao()

}