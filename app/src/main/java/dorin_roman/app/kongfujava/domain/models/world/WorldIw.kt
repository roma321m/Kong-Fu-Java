package dorin_roman.app.kongfujava.domain.models.world

import androidx.room.Entity
import androidx.room.PrimaryKey
import dorin_roman.app.kongfujava.domain.source.WorldDao.Companion.DATABASE_WORLD_IW_TABLE

@Entity(tableName = DATABASE_WORLD_IW_TABLE)
data class WorldIw(
    @PrimaryKey(autoGenerate = true)
    override var id: Int,
    override var name: String,
    override var state: Int,
    override var score: Int
):World
