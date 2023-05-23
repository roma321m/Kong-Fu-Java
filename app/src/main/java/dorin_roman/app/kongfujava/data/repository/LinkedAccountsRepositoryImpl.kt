package dorin_roman.app.kongfujava.data.repository

import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue
import dorin_roman.app.kongfujava.data.models.RequestState
import dorin_roman.app.kongfujava.domain.models.LinkedAccounts
import dorin_roman.app.kongfujava.domain.repository.LinkedAccountsRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class LinkedAccountsRepositoryImpl @Inject constructor(
    database: FirebaseDatabase
) : LinkedAccountsRepository {

    companion object {
        private const val TAG = "LinkedAccountsRepositoryImpl"
        private const val LINKED_ACCOUNTS = "Linked Accounts"
    }

    private val linkedAccountsRef = database.getReference(LINKED_ACCOUNTS)

    override suspend fun addChild(linkedAccounts: LinkedAccounts): RequestState<Boolean> {
        Log.d(TAG, "addChild: linkedAccounts:$linkedAccounts")
        return try {
            linkedAccountsRef.child(linkedAccounts.supervisorId).child(linkedAccounts.childrenId).setValue(true).await()
            RequestState.Success(true)
        } catch (exception: Exception) {
            RequestState.Error(exception)
        }
    }

    override suspend fun getLinkedAccounts(supervisorId: String): RequestState<List<String>> {
        Log.d(TAG, "getLinkedAccounts: supervisorId:$supervisorId")
        return try {
            var response: RequestState<MutableList<String>> = RequestState.Success(mutableListOf())
            linkedAccountsRef.child(supervisorId).get()
                .addOnSuccessListener { dataSnapshot ->
                    dataSnapshot.getValue<Map<String, Boolean>>()?.let { map ->
                        response = RequestState.Success(map.keys.toMutableList())
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