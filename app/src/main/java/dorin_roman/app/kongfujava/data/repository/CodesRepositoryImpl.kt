package dorin_roman.app.kongfujava.data.repository

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue
import dorin_roman.app.kongfujava.data.models.RequestState
import dorin_roman.app.kongfujava.domain.models.codes.PrivateCode
import dorin_roman.app.kongfujava.domain.models.codes.PublicCode
import dorin_roman.app.kongfujava.domain.repository.CodeRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CodesRepositoryImpl @Inject constructor(
    database: FirebaseDatabase
) : CodeRepository {

    companion object {
        private const val CODES = "Codes"
        private const val PRIVATE = "Private"
        private const val PUBLIC = "Public"
    }

    private val privateCodeRef = database.getReference(CODES).child(PRIVATE)
    private val publicCodeRef = database.getReference(CODES).child(PUBLIC)

    override suspend fun createPublicCode(publicCode: PublicCode): RequestState<Boolean> {
        return try {
            publicCodeRef.child(publicCode.code).setValue(publicCode).await()
            RequestState.Success(true)
        } catch (exception: Exception) {
            RequestState.Error(exception)
        }
    }

    override suspend fun getPublicCode(code: String): RequestState<PublicCode> {
        return try {
            var response: RequestState<PublicCode> = RequestState.Success(PublicCode())
            publicCodeRef.child(code).get()
                .addOnSuccessListener { dataSnapshot ->
                    dataSnapshot.getValue<PublicCode>()?.let { publicCode ->
                        response = RequestState.Success(publicCode)
                    }
                }.addOnFailureListener { exception ->
                    response = RequestState.Error(exception)
                }.await()
            response
        } catch (exception: Exception) {
            RequestState.Error(exception)
        }
    }

    override suspend fun createPrivateCode(privateCode: PrivateCode): RequestState<Boolean> {
        return try {
            privateCodeRef.child(privateCode.code).setValue(privateCode).await()
            RequestState.Success(true)
        } catch (exception: Exception) {
            RequestState.Error(exception)
        }
    }

    override suspend fun getPrivateCode(code: String): RequestState<PrivateCode> {
        return try {
            var response: RequestState<PrivateCode> = RequestState.Success(PrivateCode())
            privateCodeRef.child(code).get()
                .addOnSuccessListener { dataSnapshot ->
                    dataSnapshot.getValue<PrivateCode>()?.let { privateCode ->
                        response = RequestState.Success(privateCode)
                    }
                }.addOnFailureListener { exception ->
                    response = RequestState.Error(exception)
                }.await()
            response
        } catch (exception: Exception) {
            RequestState.Error(exception)
        }
    }

}