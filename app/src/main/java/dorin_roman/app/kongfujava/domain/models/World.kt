package dorin_roman.app.kongfujava.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import dorin_roman.app.kongfujava.domain.source.WorldDao.Companion.DATABASE_WORLD_TABLE

@Entity(tableName = DATABASE_WORLD_TABLE)
data class World(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var name: String,
    var state: Int,
    var score: Int
)
