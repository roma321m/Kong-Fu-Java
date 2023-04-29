package dorin_roman.app.kongfujava.domain.repository

import dorin_roman.app.kongfujava.data.models.RequestState
import dorin_roman.app.kongfujava.domain.models.LinkedAccounts

interface LinkedAccountsRepository {

    suspend fun addChild(
        linkedAccounts: LinkedAccounts
    ): RequestState<Boolean>

    suspend fun getLinkedAccounts(
        supervisorId: String
    ): RequestState<List<String>>

}