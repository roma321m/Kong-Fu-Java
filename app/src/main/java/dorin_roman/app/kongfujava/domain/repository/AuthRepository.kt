package dorin_roman.app.kongfujava.domain.repository

import com.google.firebase.auth.FirebaseUser
import dorin_roman.app.kongfujava.data.models.RequestState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow


interface AuthRepository {
    val currentUser: FirebaseUser?

    suspend fun firebaseSignUpWithEmailAndPassword(
        email: String,
        password: String
    ): RequestState<Boolean>

    suspend fun sendEmailVerification(): RequestState<Boolean>

    suspend fun firebaseSignInWithEmailAndPassword(
        email: String,
        password: String
    ): RequestState<Boolean>

    suspend fun reloadFirebaseUser(): RequestState<Boolean>

    suspend fun sendPasswordResetEmail(email: String): RequestState<Boolean>

    fun signOut()

    suspend fun revokeAccess(): RequestState<Boolean>

    fun getAuthState(viewModelScope: CoroutineScope): StateFlow<Boolean>
}