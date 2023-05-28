package dorin_roman.app.kongfujava.domain.models.levels.tutorial

import androidx.room.Entity
import androidx.room.PrimaryKey
import dorin_roman.app.kongfujava.domain.source.LevelDao


@Entity(tableName = LevelDao.DATABASE_TUTORIAL_IW_TABLE)
data class TutorialIw(
    @PrimaryKey(autoGenerate = true)
    override val id: Int,
    override var url: String,
    override val levelId: Int
) : Tutorial