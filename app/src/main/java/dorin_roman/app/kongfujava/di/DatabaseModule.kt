package dorin_roman.app.kongfujava.di

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dorin_roman.app.kongfujava.data.repository.AuthRepositoryImpl
import dorin_roman.app.kongfujava.domain.repository.AuthRepository

@Module
@InstallIn(ViewModelComponent::class)
object DatabaseModule {
    @Provides
    fun provideAuthRepository(): AuthRepository = AuthRepositoryImpl(
        auth = Firebase.auth
    )

}