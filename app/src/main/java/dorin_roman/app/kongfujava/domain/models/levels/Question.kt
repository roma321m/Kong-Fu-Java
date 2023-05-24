package dorin_roman.app.kongfujava.domain.models.levels

import androidx.room.Entity
import androidx.room.PrimaryKey
import dorin_roman.app.kongfujava.domain.source.LevelDao

@Entity(tableName = LevelDao.DATABASE_QUESTION_TABLE)
data class Question(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var title: String,
    var question: String,
)