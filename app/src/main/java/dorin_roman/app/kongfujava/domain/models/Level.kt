package dorin_roman.app.kongfujava.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import dorin_roman.app.kongfujava.domain.source.LevelDao.Companion.DATABASE_LEVEL_TABLE

@Entity(tableName = DATABASE_LEVEL_TABLE)
data class Level(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val worldId: Int,
    val number: Int,
    var state: Int,
    var score: Int,
    var type: Int,
)