package dorin_roman.app.kongfujava.data.models

sealed class DateStoreRequestState<out T> {
    object Idle : DateStoreRequestState<Nothing>()
    object Loading : DateStoreRequestState<Nothing>()
    data class Success<T>(val data: T) : DateStoreRequestState<T>()
    data class Error(val error: Throwable) : DateStoreRequestState<Nothing>()
}