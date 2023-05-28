package dorin_roman.app.kongfujava.screens.level.levels_map

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dorin_roman.app.kongfujava.LevelLogic
import dorin_roman.app.kongfujava.WorldLogic
import dorin_roman.app.kongfujava.data.models.PointState
import dorin_roman.app.kongfujava.data.models.RequestState
import dorin_roman.app.kongfujava.data.repository.LevelRepository
import dorin_roman.app.kongfujava.data.repository.WorldRepository
import dorin_roman.app.kongfujava.domain.models.levels.Level
import dorin_roman.app.kongfujava.screens.level.LevelType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LevelsMapViewModel @Inject constructor(
    private val levelRepository: LevelRepository,
    private val worldRepository: WorldRepository
) : ViewModel() {

    companion object {
        const val TAG = "LevelsViewModel"
    }

    var levelsModels by mutableStateOf(listOf<LevelItemModel>())
        private set

    private var currentWorldId: Int = -1

    private var currentWorldState: PointState = PointState.ZERO

    private val levels = MutableStateFlow<RequestState<List<Level>>>(RequestState.Idle)

    fun handle(event: LevelsMapEvent) {
        when (event) {
            is LevelsMapEvent.InitLevels -> initLevels(event.worldId)
        }
    }

    private fun initLevels(worldId: Int) {
        Log.d(TAG, "initLevels")
        currentWorldId = worldId
        loadAllLevels()
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
        var currentWorldScore = 0
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
            currentWorldScore += level.score
        }
        levelsModels = levelItemModels
        updateWorld(currentWorldScore)
    }

    private fun getLevelState(state: Int): PointState {
        Log.d(TAG, "getLevelState")
        return LevelLogic.getLevelState(state)
    }

    private fun getLevelType(type: Int): LevelType {
        Log.d(TAG, "getLevelType")
        return LevelLogic.getLevelType(type)
    }

    private fun updateWorld(currentWorldScore: Int) = viewModelScope.launch(Dispatchers.IO) {
        Log.d(TAG, "updateWorld")
        currentWorldState = WorldLogic.getWorldStateByScore(currentWorldScore)
        worldRepository.updateWorld(currentWorldId, currentWorldState.ordinal, currentWorldScore)
    }

}


