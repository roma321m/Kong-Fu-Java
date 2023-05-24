package dorin_roman.app.kongfujava.screens.level.levels_map

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dorin_roman.app.kongfujava.LevelLogic
import dorin_roman.app.kongfujava.data.models.PointState
import dorin_roman.app.kongfujava.data.models.RequestState
import dorin_roman.app.kongfujava.data.repository.LevelRepository
import dorin_roman.app.kongfujava.domain.models.levels.Level
import dorin_roman.app.kongfujava.screens.level.LevelType
import dorin_roman.app.kongfujava.screens.level.levels_map.components.LevelsEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LevelsMapViewModel @Inject constructor(
    private val levelRepository: LevelRepository,
) : ViewModel() {

    companion object {
        const val TAG = "LevelsViewModel"
    }

    var levelsModels by mutableStateOf(listOf<LevelItemModel>())
        private set

    private var currentWorldId: Int = -1

    private val levels = MutableStateFlow<RequestState<List<Level>>>(RequestState.Idle)

    fun handle(event: LevelsEvent) {
        when (event) {
            is LevelsEvent.InitLevels -> initLevels(event.worldId)
            is LevelsEvent.UpdateLevel -> updateWorld(event.level)
            is LevelsEvent.UpdateLevelScore -> updateWorldScore(event.score)
            is LevelsEvent.UpdateLevelState -> updateWorldState(event.state)
        }
    }

    private fun initLevels(worldId: Int) {
        Log.d(TAG, "initLevels")
        currentWorldId = worldId
        loadAllLevels()
    }

    private fun updateWorldState(state: PointState) {
        //TODO
    }

    private fun updateWorldScore(score: Int) {
        //TODO
    }

    private fun updateWorld(level: Level) {
        //TODO
    }

    private fun loadAllLevels() = viewModelScope.launch(Dispatchers.IO) {
        Log.d(TAG, "loadAllLevels")
        levels.value = RequestState.Loading
        try {
            levelRepository.getAllLevels(currentWorldId).collect { levels ->
                this@LevelsMapViewModel.levels.value = RequestState.Success(levels)
                buildLevelsItemModels(levels)
            }
        } catch (e: Exception) {
            Log.e(TAG, "${e.message}")
            levels.value = RequestState.Error(e)
        }
    }


    private fun buildLevelsItemModels(levels: List<Level>) {
        Log.d(TAG, "buildLevelsItemModels ${levels.size}")
        val levelItemModels = mutableListOf<LevelItemModel>()
        levels.forEach { level ->
            levelItemModels.add(
                LevelItemModel(
                    levelId = level.id,
                    levelState = getLevelState(level.state),
                    levelNumber = level.number,
                    levelType = getLevelType(level.type),
                    levelScore = level.score,
                )
            )
        }
        levelsModels = levelItemModels
    }

    private fun getLevelState(state: Int): PointState {
        return LevelLogic.getLevelState(state)
    }

    private fun getLevelType(type: Int): LevelType {
        return LevelLogic.getLevelType(type)
    }

}


