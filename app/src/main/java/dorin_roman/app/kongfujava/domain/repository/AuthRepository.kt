package dorin_roman.app.kongfujava.domain.repository

import com.google.firebase.auth.FirebaseUser
import dorin_roman.app.kongfujava.domain.model.FirebaseRequestState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow


interface AuthRepository {
    val currentUser: FirebaseUser?

    suspend fun firebaseSignUpWithEmailAndPassword(
        email: String,
        password: String
    ): FirebaseRequestState<Boolean>

    suspend fun sendEmailVerification(): FirebaseRequestState<Boolean>

    suspend fun firebaseSignInWithEmailAndPassword(
        email: String,
        password: String
    ): FirebaseRequestState<Boolean>

    suspend fun reloadFirebaseUser(): FirebaseRequestState<Boolean>

    suspend fun sendPasswordResetEmail(email: String): FirebaseRequestState<Boolean>

    fun signOut()

    suspend fun revokeAccess(): FirebaseRequestState<Boolean>

    fun getAuthState(viewModelScope: CoroutineScope): StateFlow<Boolean>
}