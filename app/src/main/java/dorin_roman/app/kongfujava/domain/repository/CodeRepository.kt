package dorin_roman.app.kongfujava.domain.repository

import dorin_roman.app.kongfujava.data.models.RequestState
import dorin_roman.app.kongfujava.domain.models.codes.PrivateCode
import dorin_roman.app.kongfujava.domain.models.codes.PublicCode

interface CodeRepository {

    suspend fun createPublicCode(
        publicCode: PublicCode
    ): RequestState<Boolean>

    suspend fun getPublicCode(
        code: String
    ): RequestState<PublicCode>

    suspend fun createPrivateCode(
        privateCode: PrivateCode
    ): RequestState<Boolean>

    suspend fun getPrivateCode(
        code: String
    ): RequestState<PrivateCode>
}