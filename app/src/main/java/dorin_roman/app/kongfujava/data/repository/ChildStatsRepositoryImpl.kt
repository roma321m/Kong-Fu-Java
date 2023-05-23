package dorin_roman.app.kongfujava.data.repository

import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue
import dorin_roman.app.kongfujava.data.models.RequestState
import dorin_roman.app.kongfujava.domain.models.child_stats.ActiveTime
import dorin_roman.app.kongfujava.domain.models.child_stats.LevelStats
import dorin_roman.app.kongfujava.domain.repository.ChildStatsRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ChildStatsRepositoryImpl @Inject constructor(
    database: FirebaseDatabase
) : ChildStatsRepository {

    companion object {
        private const val TAG = "ChildStatsRepositoryImpl"
        private const val CHILDREN_STATS = "Children Statistics"
        private const val ACTIVE_TIME = "Active Time"
        private const val LEVELS = "Levels"
    }

    private val activeTimeRef = database.getReference(CHILDREN_STATS).child(ACTIVE_TIME)
    private val levelsRef = database.getReference(CHILDREN_STATS).child(LEVELS)

    override suspend fun addActiveTime(
        childId: String,
        activeTime: ActiveTime
    ): RequestState<Boolean> {
        Log.d(TAG, "addActiveTime: childId:$childId, activeTime:$activeTime")
        return try {
            activeTimeRef.child(childId).child(activeTime.fromInMilli.toString())
                .setValue(activeTime).await()
            RequestState.Success(true)
        } catch (exception: Exception) {
            RequestState.Error(exception)
        }
    }

    override suspend fun getActiveTimeList(
        childId: String
    ): RequestState<List<ActiveTime>> {
        Log.d(TAG, "getActiveTimeList: childId:$childId")
        return try {
            var response: RequestState<MutableList<ActiveTime>> =
                RequestState.Success(mutableListOf())
            activeTimeRef.child(childId).get()
                .addOnSuccessListener { dataSnapshot ->
                    dataSnapshot.getValue<Map<String, ActiveTime>>()?.let { map ->
                        response = RequestState.Success(map.values.toMutableList())
                    }
                }.addOnFailureListener { exception ->
                    response = RequestState.Error(exception)
                }.await()
            response
        } catch (exception: Exception) {
            RequestState.Error(exception)
        }
    }

    override suspend fun addLevelStats(
        childId: String,
        levelStats: LevelStats
    ): RequestState<Boolean> {
        Log.d(TAG, "addLevelStats: childId:$childId, levelStats:$levelStats")
        return try {
            levelsRef.child(childId).child(levelStats.id.toString())
                .setValue(levelStats).await()
            RequestState.Success(true)
        } catch (exception: Exception) {
            RequestState.Error(exception)
        }
    }

    override suspend fun getLevelStats(
        childId: String,
        levelId: String
    ): RequestState<LevelStats> {
        Log.d(TAG, "getLevelStats: childId:$childId, levelId:$levelId")
        return try {
            var response: RequestState<LevelStats> = RequestState.Success(LevelStats())
            levelsRef.child(childId).child(levelId).get()
                .addOnSuccessListener { dataSnapshot ->
                    dataSnapshot.getValue<LevelStats>()?.let { level ->
                        response = RequestState.Success(level)
                    }
                }.addOnFailureListener { exception ->
                    response = RequestState.Error(exception)
                }.await()
            response
        } catch (exception: Exception) {
            RequestState.Error(exception)
        }
    }

    override suspend fun getLevelStatsList(
        childId: String
    ): RequestState<List<LevelStats>> {
        Log.d(TAG, "getLevelStatsList")
        return try {
            var response: RequestState<MutableList<LevelStats>> =
                RequestState.Success(mutableListOf())
            levelsRef.child(childId).get()
                .addOnSuccessListener { dataSnapshot ->
                    dataSnapshot.getValue<ArrayList<LevelStats>>()?.let { list ->
                        response = RequestState.Success(list)
                    }
                }.addOnFailureListener { exception ->
                    response = RequestState.Error(exception)
                }.await()
            response
        } catch (exception: Exception) {
            RequestState.Error(exception)
        }
    }

}