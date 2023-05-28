package dorin_roman.app.kongfujava.ui.components.topbar

import android.media.MediaPlayer
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dorin_roman.app.kongfujava.data.models.UserType
import dorin_roman.app.kongfujava.data.repository.ChildIdRepository
import dorin_roman.app.kongfujava.data.repository.MusicRepository
import dorin_roman.app.kongfujava.data.repository.UserTypeRepository
import dorin_roman.app.kongfujava.domain.repository.DeleteDB
import dorin_roman.app.kongfujava.ui.toast.ToastLauncher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TopBarViewModel @Inject constructor(
    val mediaPlayer: MediaPlayer,
    private val childIdRepository: ChildIdRepository,
    private val userTypeRepository: UserTypeRepository,
    private val musicRepository: MusicRepository,
    private val toastLauncher: ToastLauncher,
    private val deleteDB: DeleteDB
) : ViewModel() {

    companion object {
        const val TAG = "TopBarViewModel"
    }

    var isMusicPlaying by mutableStateOf(false)
        private set

    init {
        Log.d(TAG, "init")
        readIsActive()
    }

    fun handle(event: TopBarEvent) {
        when (event) {
            TopBarEvent.AboutClicked -> aboutClicked()
            TopBarEvent.LogOutClicked -> logoutClicked()
            TopBarEvent.MusicClicked -> musicClicked()
            TopBarEvent.Pause -> pauseMusic()
            TopBarEvent.Resume -> resumeMusic()
        }
    }

    private fun musicClicked() {
        Log.d(TAG, "musicClicked")
        if (isMusicPlaying) {
            stopMusic()
        } else {
            startMusic()
        }
        persistIsMusicActive()
    }

    private fun aboutClicked() {
        Log.d(TAG, "aboutClicked")
        toastLauncher.launch(TopBarToast.Thanks)
    }

    private fun logoutClicked() {
        Log.d(TAG, "logout")
        persistChildId()
        persistUserType()
        deleteDB.launch()
    }

    private fun startMusic() {
        Log.d(TAG, "startMusic")
        mediaPlayer.start()
        isMusicPlaying = true
    }

    private fun stopMusic() {
        Log.d(TAG, "stopMusic")
        mediaPlayer.pause()
        isMusicPlaying = false
    }

    private fun resumeMusic() {
        Log.d(TAG, "resumeMusic")
        if (isMusicPlaying) {
            mediaPlayer.start()
        }
    }

    private fun pauseMusic() {
        Log.d(TAG, "pauseMusic")
        if (isMusicPlaying) {
            mediaPlayer.pause()
        }
    }

    private fun persistIsMusicActive() = viewModelScope.launch(Dispatchers.IO) {
        Log.d(TAG, "persistIsMusicActive")
        musicRepository.persistMusic(isMusicPlaying)
    }

    private fun readIsActive() = viewModelScope.launch {
        Log.d(TAG, "readIsActive")
        try {
            musicRepository.readIsActive
                .map { it }
                .collect {
                    if (it) {
                        startMusic()
                    }
                }
        } catch (exception: Exception) {
            Log.e(TAG, "${exception.message}")
        }
    }

    private fun persistChildId() = viewModelScope.launch(Dispatchers.IO) {
        Log.d(TAG, "persistChildId")
        childIdRepository.persistChildId("")
    }

    private fun persistUserType() = viewModelScope.launch(Dispatchers.IO) {
        Log.d(TAG, "persistUserType")
        userTypeRepository.persistUserType(UserType.None)
    }

}