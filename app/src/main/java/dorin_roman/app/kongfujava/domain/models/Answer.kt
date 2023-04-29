package dorin_roman.app.kongfujava.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import dorin_roman.app.kongfujava.domain.source.LevelDao.Companion.DATABASE_ANSWER_TABLE

@Entity(tableName = DATABASE_ANSWER_TABLE)
data class Answer(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val levelId: Int,
    var answer1: String,
    var answer2: String,
    var answer3: String,
    var answer4: String,
    var right: Int,
)