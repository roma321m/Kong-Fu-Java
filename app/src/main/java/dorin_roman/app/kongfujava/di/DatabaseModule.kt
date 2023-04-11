package dorin_roman.app.kongfujava.di

import android.content.Context
import androidx.room.Room
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dorin_roman.app.kongfujava.data.repository.AuthRepositoryImpl
import dorin_roman.app.kongfujava.data.repository.UsersRepositoryImpl
import dorin_roman.app.kongfujava.domain.repository.AuthRepository
import dorin_roman.app.kongfujava.domain.repository.UsersRepository
import dorin_roman.app.kongfujava.domain.source.KongFuDataBase
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object DatabaseModule {

    private const val DATABASE_NAME = "kong_fu_db"

    @Provides
    fun provideAuthRepository(): AuthRepository = AuthRepositoryImpl(
        auth = Firebase.auth
    )

    @Provides
    fun provideUsersRepository(): UsersRepository = UsersRepositoryImpl(
        database = FirebaseDatabase.getInstance()
    )

    @Provides
    fun provideDataBase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        KongFuDataBase::class.java,
        DATABASE_NAME
    ).createFromAsset("database/world.db").build()

    @Provides
    fun provideWorldDao(dataBase: KongFuDataBase) = dataBase.worldDao()


}