package dorin_roman.app.kongfujava.domain.models.levels.question

import androidx.room.Entity
import androidx.room.PrimaryKey
import dorin_roman.app.kongfujava.domain.source.LevelDao

@Entity(tableName = LevelDao.DATABASE_QUESTION_TABLE)
data class QuestionEn(
    @PrimaryKey(autoGenerate = true)
    override val id: Int,
    override var title: String,
    override var question: String,
) : Question