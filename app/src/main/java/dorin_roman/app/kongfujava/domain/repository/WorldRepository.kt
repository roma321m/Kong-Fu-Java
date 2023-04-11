package dorin_roman.app.kongfujava.domain.repository

import dagger.hilt.android.scopes.ViewModelScoped
import dorin_roman.app.kongfujava.domain.source.WorldDao
import javax.inject.Inject

@ViewModelScoped
class WorldRepository @Inject constructor(
    private val worldDao: WorldDao
) {
    val getAllWorlds = worldDao.getAllWorlds()
}