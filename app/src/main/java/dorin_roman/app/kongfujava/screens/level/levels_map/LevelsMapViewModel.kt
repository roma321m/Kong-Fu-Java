package dorin_roman.app.kongfujava.screens.level.levels_map

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dorin_roman.app.kongfujava.data.models.PointState
import dorin_roman.app.kongfujava.data.models.RequestState
import dorin_roman.app.kongfujava.data.repository.LevelRepository
import dorin_roman.app.kongfujava.domain.models.Level
import dorin_roman.app.kongfujava.screens.level.LevelType
import dorin_roman.app.kongfujava.screens.level.levels_map.components.LevelsEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LevelsMapViewModel @Inject constructor(
    private val levelRepository: LevelRepository
) : ViewModel() {
    companion object {
        const val TAG = "LevelsViewModel"
    }

    var levelsModels = mutableStateListOf<LevelItemModel>()
        private set

    private var currentWorld: Int = -1

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
        currentWorld = worldId
        getAllLevels()
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

    private fun getAllLevels() {
        Log.d(TAG, "getAllLevels")
        levels.value = RequestState.Loading
        try {
            viewModelScope.launch {
                levelRepository.getAllLevels(currentWorld).collect { levels ->
                    this@LevelsMapViewModel.levels.value = RequestState.Success(levels)
                    buildLevelsItemModels(levels)
                }
            }
        } catch (e: Exception) {
            levels.value = RequestState.Error(e)
        }
    }

    private fun buildLevelsItemModels(levels: List<Level>) {

        levels.forEach { level ->
            levelsModels.add(
                LevelItemModel(
                    levelId = level.id,
                    levelState = getLevelState(level.state),
                    levelNumber = level.number,
                    levelType = getLevelType(level.type),
                    levelScore = level.score,
                )
            )
        }
    }

    private fun getLevelState(state: Int): PointState {
        when (state) {
            PointState.LOCK.ordinal -> return PointState.LOCK
            PointState.ZERO.ordinal -> return PointState.ZERO
            PointState.ONE.ordinal -> return PointState.ONE
            PointState.TWO.ordinal -> return PointState.TWO
            PointState.THREE.ordinal -> return PointState.THREE
        }
        return PointState.LOCK
    }

    private fun getLevelType(type: Int): LevelType {
        when (type) {
            LevelType.TUTORIAL.ordinal -> return LevelType.TUTORIAL
            LevelType.MULTI.ordinal -> return LevelType.MULTI
            LevelType.DRAG_DROP.ordinal -> return LevelType.DRAG_DROP
        }
        return LevelType.TUTORIAL
    }

}


