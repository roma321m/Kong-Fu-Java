package dorin_roman.app.kongfujava.data.repository

import com.google.firebase.database.FirebaseDatabase
import dorin_roman.app.kongfujava.data.models.RequestState
import dorin_roman.app.kongfujava.domain.models.codes.PrivateCode
import dorin_roman.app.kongfujava.domain.models.codes.PublicCode
import dorin_roman.app.kongfujava.domain.repository.CodeRepository
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
        TODO("Not yet implemented")
    }

    override suspend fun getPublicCode(code: String): RequestState<PublicCode> {
        TODO("Not yet implemented")
    }

    override suspend fun createPrivateCode(privateCode: PrivateCode): RequestState<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun getPrivateCode(code: String): RequestState<PrivateCode> {
        TODO("Not yet implemented")
    }

}