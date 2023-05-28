package dorin_roman.app.kongfujava.domain.models.levels.answer

import androidx.room.Entity
import androidx.room.PrimaryKey
import dorin_roman.app.kongfujava.domain.source.LevelDao.Companion.DATABASE_ANSWER_TABLE

@Entity(tableName = DATABASE_ANSWER_TABLE)
data class AnswerEn(
    @PrimaryKey(autoGenerate = true)
    override val id: Int,
    override val levelId: Int,
    override var answer1: String,
    override var answer2: String,
    override var answer3: String,
    override var answer4: String,
    override var right: String,
) : Answer