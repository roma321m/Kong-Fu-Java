package dorin_roman.app.kongfujava.domain.source

import androidx.room.Database
import androidx.room.RoomDatabase
import dorin_roman.app.kongfujava.domain.models.World

@Database(
    entities = [World::class],
    version = 1,
    exportSchema = true
)
abstract class KongFuDataBase: RoomDatabase() {

    abstract fun worldDao(): WorldDao

}