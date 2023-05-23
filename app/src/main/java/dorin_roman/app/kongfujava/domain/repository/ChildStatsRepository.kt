package dorin_roman.app.kongfujava.domain.repository

import dorin_roman.app.kongfujava.data.models.RequestState
import dorin_roman.app.kongfujava.domain.models.child_stats.ActiveTime
import dorin_roman.app.kongfujava.domain.models.child_stats.LevelStats

interface ChildStatsRepository {

    suspend fun addActiveTime(
        childId: String,
        activeTime: ActiveTime
    ): RequestState<Boolean>

    suspend fun getActiveTimeList(
        childId: String
    ): RequestState<List<ActiveTime>>

    suspend fun addLevelStats(
        childId: String,
        levelStats: LevelStats
    ): RequestState<Boolean>

    suspend fun getLevelStats(
        childId: String,
        levelId: String,
    ): RequestState<LevelStats>

    suspend fun getLevelStatsList(
        childId: String,
    ): RequestState<List<LevelStats>>
}