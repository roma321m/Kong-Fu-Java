package dorin_roman.app.kongfujava.data.repository

import android.content.Context
import android.util.LayoutDirection
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dorin_roman.app.kongfujava.domain.models.world.World
import dorin_roman.app.kongfujava.domain.source.WorldDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class WorldRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val worldDao: WorldDao
) {
    fun getAllWorlds(): Flow<List<World>> {
        return if (context.resources.configuration.layoutDirection == LayoutDirection.RTL) {
            worldDao.getAllWorldsIw()
        } else {
            worldDao.getAllWorlds()
        }
    }

    fun updateWorld(id: Int, state: Int, score: Int) {
        worldDao.updateWorld(id, state, score)
        worldDao.updateWorldIw(id, state, score)
    }
}