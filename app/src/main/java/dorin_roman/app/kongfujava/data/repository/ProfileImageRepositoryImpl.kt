package dorin_roman.app.kongfujava.data.repository

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import dorin_roman.app.kongfujava.data.models.RequestState
import dorin_roman.app.kongfujava.domain.repository.ProfileImageRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProfileImageRepositoryImpl @Inject constructor(
    private val storage: FirebaseStorage
) : ProfileImageRepository {

    companion object {
        const val IMAGES = "images"
        const val IMAGE_SUFFIX = ".jpg"
    }

    override suspend fun addImageToCouldStorage(uid: String, imageUri: Uri): RequestState<String> {
        return try {
            val downloadUrl = storage.reference.child(IMAGES).child(uid + IMAGE_SUFFIX)
                .putFile(imageUri).await()
                .storage.downloadUrl.await()
            RequestState.Success(downloadUrl.toString())
        } catch (e: Exception) {
            RequestState.Error(e)
        }
    }

}