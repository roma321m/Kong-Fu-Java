package dorin_roman.app.kongfujava.screens.worlds

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dorin_roman.app.kongfujava.data.models.PointState
import dorin_roman.app.kongfujava.data.models.RequestState
import dorin_roman.app.kongfujava.data.models.UserType
import dorin_roman.app.kongfujava.data.repository.ChildIdRepository
import dorin_roman.app.kongfujava.data.repository.UserTypeRepository
import dorin_roman.app.kongfujava.domain.models.World
import dorin_roman.app.kongfujava.data.repository.WorldRepository
import dorin_roman.app.kongfujava.screens.worlds.components.WorldsEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorldsMapViewModel @Inject constructor(
    private val childIdRepository: ChildIdRepository,
    private val userTypeRepository: UserTypeRepository,
    private val worldRepository: WorldRepository
) : ViewModel() {

    companion object {
        const val TAG = "WorldsMapViewModel"
    }

    private val _childId = MutableStateFlow<RequestState<String>>(RequestState.Idle)
    val childId: StateFlow<RequestState<String>> = _childId // Fixme - use to store progress data on firebase for this child

    private val _worlds = MutableStateFlow<RequestState<List<World>>>(RequestState.Idle)
    val worlds: StateFlow<RequestState<List<World>>> = _worlds

    init {
        Log.d(TAG, "init")
        readChildId()
        getAllWorlds()
    }

    fun handle(event: WorldsEvent) {
        when (event) {
            WorldsEvent.LogOut -> logout()
            is WorldsEvent.UpdateWorld -> updateWorld(event.world)
            is WorldsEvent.UpdateWorldScore -> updateWorldScore(event.score)
            is WorldsEvent.UpdateWorldState -> updateWorldState(event.state)
        }
    }

    private fun readChildId() {
        Log.d(TAG, "readChildId")
        _childId.value = RequestState.Loading
        try {
            viewModelScope.launch {
                childIdRepository.readChildId
                    .map { it }
                    .collect { _childId.value = RequestState.Success(it) }
            }
        } catch (exception: Exception) {
            _childId.value = RequestState.Error(exception)
        }
    }

    private fun persistChildId() {
        Log.d(TAG, "persistChildId")
        viewModelScope.launch(Dispatchers.IO) {
            childIdRepository.persistChildId("")
        }
    }

    private fun persistUserType() {
        Log.d(TAG, "persistUserType")
        viewModelScope.launch(Dispatchers.IO) {
            userTypeRepository.persistUserType(UserType.None)
        }
    }

    private fun logout() {
        Log.d(TAG, "logout")
        persistChildId()
        persistUserType()
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