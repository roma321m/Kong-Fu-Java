package dorin_roman.app.kongfujava.domain.source

import androidx.room.Dao
import androidx.room.Query
import dorin_roman.app.kongfujava.domain.models.World
import kotlinx.coroutines.flow.Flow

//Dao - Data Access Object
@Dao
interface WorldDao {
    companion object{
        const val DATABASE_WORLD_TABLE = "world"
    }


    @Query("SELECT * FROM world")
    fun getAllWorlds(): Flow<List<World>>


}