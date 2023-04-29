package dorin_roman.app.kongfujava.screens.worlds

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dorin_roman.app.kongfujava.data.models.PointState
import dorin_roman.app.kongfujava.data.models.RequestState
import dorin_roman.app.kongfujava.domain.models.World
import dorin_roman.app.kongfujava.data.repository.WorldRepository
import dorin_roman.app.kongfujava.screens.worlds.components.WorldsEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorldsMapViewModel @Inject constructor(
    private val worldRepository: WorldRepository
) : ViewModel() {

    companion object {
        const val TAG = "WorldsMapViewModel"
    }

    private val _worlds = MutableStateFlow<RequestState<List<World>>>(RequestState.Idle)
    val worlds: StateFlow<RequestState<List<World>>> = _worlds

    init {
        getAllWorlds()
    }

    fun handle(event: WorldsEvent) {
        when (event) {
            is WorldsEvent.UpdateWorld -> updateWorld(event.world)
            is WorldsEvent.UpdateWorldScore -> updateWorldScore(event.score)
            is WorldsEvent.UpdateWorldState -> updateWorldState(event.state)
        }
    }

    private fun updateWorldState(state: PointState) {
        //TODO
    }

    private fun updateWorldScore(score: Int) {
        //TODO
    }

    private fun updateWorld(world: World) {
        //TODO
    }

    private fun getAllWorlds() {
        Log.d(TAG, "getAllWorlds")
        _worlds.value = RequestState.Loading
        try {
            viewModelScope.launch {
                worldRepository.getAllWorlds.collect {
                    _worlds.value = RequestState.Success(it)
                }
            }
        } catch (e: Exception) {
            _worlds.value = RequestState.Error(e)
        }
    }

}