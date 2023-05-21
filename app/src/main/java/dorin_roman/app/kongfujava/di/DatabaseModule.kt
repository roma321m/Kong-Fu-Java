package dorin_roman.app.kongfujava.di

import android.content.Context
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dorin_roman.app.kongfujava.data.repository.AuthRepositoryImpl
import dorin_roman.app.kongfujava.data.repository.CodesRepositoryImpl
import dorin_roman.app.kongfujava.data.repository.LinkedAccountsRepositoryImpl
import dorin_roman.app.kongfujava.data.repository.ProfileImageRepositoryImpl
import dorin_roman.app.kongfujava.data.repository.UsersRepositoryImpl
import dorin_roman.app.kongfujava.di.provider.CodeProvider
import dorin_roman.app.kongfujava.di.provider.IdProvider
import dorin_roman.app.kongfujava.di.provider.KongFuDatabaseProvider
import dorin_roman.app.kongfujava.domain.repository.AuthRepository
import dorin_roman.app.kongfujava.domain.repository.CodeRepository
import dorin_roman.app.kongfujava.domain.repository.LinkedAccountsRepository
import dorin_roman.app.kongfujava.domain.repository.ProfileImageRepository
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
    fun provideProfileImageRepository(): ProfileImageRepository = ProfileImageRepositoryImpl(
        storage = Firebase.storage
    )

    @Provides
    fun provideUsersRepository(): UsersRepository = UsersRepositoryImpl(
        database = Firebase.database
    )

    @Provides
    fun provideCodesRepository(): CodeRepository = CodesRepositoryImpl(
        database = Firebase.database
    )

    @Provides
    fun provideLinkedAccountsRepository(): LinkedAccountsRepository = LinkedAccountsRepositoryImpl(
        database = Firebase.database
    )

    @Provides
    fun provideCode(): CodeProvider = CodeProvider

    @Provides
    fun provideId(): IdProvider = IdProvider

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