package dorin_roman.app.kongfujava.screens.level.tutorial

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dorin_roman.app.kongfujava.data.models.RequestState
import dorin_roman.app.kongfujava.data.repository.LevelRepository
import dorin_roman.app.kongfujava.domain.models.levels.Tutorial
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TutorialViewModel @Inject constructor(
    private val levelRepository: LevelRepository
) : ViewModel() {

    companion object {
        const val TAG = "TutorialViewModel"
    }

    var videoUrl by mutableStateOf("")
        private set

    private var currentLevelId: Int = -1

    private val tutorial = MutableStateFlow<RequestState<Tutorial>>(RequestState.Idle)

    fun handle(event: TutorialEvent) {
        when (event) {
            is TutorialEvent.InitTutorial -> initTutorial(event.levelId)
        }
    }

    private fun initTutorial(levelId: Int) {
        Log.d(TAG, "initTutorial: $levelId")
        currentLevelId = levelId
        loadVideoUrl()
    }

    private fun loadVideoUrl() = viewModelScope.launch {
        Log.d(TAG, "loadVideoUrl")
        tutorial.value = RequestState.Loading
        try {
            levelRepository.getTutorial(currentLevelId).collect { tutorial ->
                this@TutorialViewModel.tutorial.value = RequestState.Success(tutorial)
                videoUrl = tutorial.url
            }
        } catch (e: Exception) {
            Log.e(TAG, "${e.message}")
            tutorial.value = RequestState.Error(e)
        }
    }

}