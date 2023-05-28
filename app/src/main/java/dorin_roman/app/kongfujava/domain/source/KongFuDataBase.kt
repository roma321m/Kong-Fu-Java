package dorin_roman.app.kongfujava.domain.source

import androidx.room.Database
import androidx.room.RoomDatabase
import dorin_roman.app.kongfujava.domain.models.levels.Level
import dorin_roman.app.kongfujava.domain.models.levels.answer.AnswerEn
import dorin_roman.app.kongfujava.domain.models.levels.answer.AnswerIw
import dorin_roman.app.kongfujava.domain.models.levels.question.QuestionEn
import dorin_roman.app.kongfujava.domain.models.levels.question.QuestionIw
import dorin_roman.app.kongfujava.domain.models.levels.tutorial.TutorialEn
import dorin_roman.app.kongfujava.domain.models.levels.tutorial.TutorialIw
import dorin_roman.app.kongfujava.domain.models.world.WorldEn
import dorin_roman.app.kongfujava.domain.models.world.WorldIw

@Database(
    entities = [
        WorldEn::class,
        WorldIw::class,
        Level::class,
        AnswerEn::class,
        AnswerIw::class,
        QuestionEn::class,
        QuestionIw::class,
        TutorialEn::class,
        TutorialIw::class
    ],
    version = 1,
    exportSchema = true
)
abstract class KongFuDataBase : RoomDatabase() {

    abstract fun worldDao(): WorldDao

    abstract fun levelDao(): LevelDao

}