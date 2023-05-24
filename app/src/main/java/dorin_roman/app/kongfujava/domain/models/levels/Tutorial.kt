package dorin_roman.app.kongfujava.domain.models.levels

import androidx.room.Entity
import androidx.room.PrimaryKey
import dorin_roman.app.kongfujava.domain.source.LevelDao


@Entity(tableName = LevelDao.DATABASE_TUTORIAL_TABLE)
data class Tutorial(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var url: String,
    val levelId: Int
)