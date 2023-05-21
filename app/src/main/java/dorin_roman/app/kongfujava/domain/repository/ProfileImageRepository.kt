package dorin_roman.app.kongfujava.domain.repository

import android.net.Uri
import dorin_roman.app.kongfujava.data.models.RequestState

interface ProfileImageRepository {

    suspend fun addImageToCouldStorage(uid: String, imageUri: Uri): RequestState<String>

}