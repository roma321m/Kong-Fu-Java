package dorin_roman.app.kongfujava.domain.model

sealed class FirebaseRequestState<out T> {
    object Loading : FirebaseRequestState<Nothing>()
    data class Success<out T>(val data: T) : FirebaseRequestState<T>()
    data class Failure(val e: Exception) : FirebaseRequestState<Nothing>()
}