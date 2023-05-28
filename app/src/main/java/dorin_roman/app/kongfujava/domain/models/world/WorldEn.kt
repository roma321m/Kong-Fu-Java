package dorin_roman.app.kongfujava.domain.models.world

import androidx.room.Entity
import androidx.room.PrimaryKey
import dorin_roman.app.kongfujava.domain.source.WorldDao.Companion.DATABASE_WORLD_TABLE

@Entity(tableName = DATABASE_WORLD_TABLE)
data class WorldEn(
    @PrimaryKey(autoGenerate = true)
    override var id: Int = -1,
    override var name: String = "",
    override var state: Int = 0,
    override var score: Int = 0
):World
