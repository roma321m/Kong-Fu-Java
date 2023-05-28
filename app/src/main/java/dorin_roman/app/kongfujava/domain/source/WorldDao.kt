package dorin_roman.app.kongfujava.domain.source

import androidx.room.Dao
import androidx.room.Query
import dorin_roman.app.kongfujava.domain.models.world.WorldEn
import dorin_roman.app.kongfujava.domain.models.world.WorldIw
import kotlinx.coroutines.flow.Flow

//Dao - Data Access Object
@Dao
interface WorldDao {

    companion object{
        const val DATABASE_WORLD_TABLE = "world"
        const val DATABASE_WORLD_IW_TABLE = "world_iw"
    }

    @Query("SELECT * FROM world")
    fun getAllWorlds(): Flow<List<WorldEn>>

    @Query("SELECT * FROM world_iw")
    fun getAllWorldsIw(): Flow<List<WorldIw>>

    @Query("UPDATE world SET state = :state, score = :score WHERE id =:id")
    fun updateWorld(id: Int, state: Int, score: Int)

    @Query("UPDATE world_iw SET state = :state, score = :score WHERE id =:id")
    fun updateWorldIw(id: Int, state: Int, score: Int)

}