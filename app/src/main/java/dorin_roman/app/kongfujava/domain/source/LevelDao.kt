package dorin_roman.app.kongfujava.domain.source

import androidx.room.Dao
import androidx.room.Query
import dorin_roman.app.kongfujava.domain.models.levels.Level
import dorin_roman.app.kongfujava.domain.models.levels.answer.AnswerEn
import dorin_roman.app.kongfujava.domain.models.levels.answer.AnswerIw
import dorin_roman.app.kongfujava.domain.models.levels.question.QuestionEn
import dorin_roman.app.kongfujava.domain.models.levels.question.QuestionIw
import dorin_roman.app.kongfujava.domain.models.levels.tutorial.TutorialEn
import dorin_roman.app.kongfujava.domain.models.levels.tutorial.TutorialIw
import kotlinx.coroutines.flow.Flow

@Dao
interface LevelDao {

    companion object{
        const val DATABASE_LEVEL_TABLE = "level"
        const val DATABASE_QUESTION_TABLE = "question"
        const val DATABASE_QUESTION_IW_TABLE = "question_iw"
        const val DATABASE_ANSWER_TABLE = "answer"
        const val DATABASE_ANSWER_IW_TABLE = "answer_iw"
        const val DATABASE_TUTORIAL_TABLE = "tutorial"
        const val DATABASE_TUTORIAL_IW_TABLE = "tutorial_iw"
    }

    @Query("SELECT * FROM level WHERE worldId=:worldId")
    fun getAllLevels(
        worldId: Int
    ): Flow<List<Level>>

    @Query("SELECT * FROM level WHERE id=:id")
    fun getLevel(
        id: Int
    ): Flow<Level>

    @Query("UPDATE level SET score = :score WHERE id =:id")
    fun updateLevelScore(id: Int, score: Int)

    @Query("UPDATE level SET state = :state WHERE id =:id")
    fun updateLevelState(id: Int, state: Int)

    @Query("SELECT * FROM question WHERE id=:id")
    fun getQuestion(
        id: Int
    ): Flow<QuestionEn>

    @Query("SELECT * FROM question_iw WHERE id=:id")
    fun getQuestionIw(
        id: Int
    ): Flow<QuestionIw>

    @Query("SELECT * FROM answer WHERE levelId=:levelId")
    fun getAnswer(
        levelId: Int
    ): Flow<AnswerEn>

    @Query("SELECT * FROM answer_iw WHERE levelId=:levelId")
    fun getAnswerIw(
        levelId: Int
    ): Flow<AnswerIw>

    @Query("SELECT * FROM tutorial WHERE levelId=:levelId")
    fun getTutorial(
        levelId: Int
    ): Flow<TutorialEn>

    @Query("SELECT * FROM tutorial_iw WHERE levelId=:levelId")
    fun getTutorialIw(
        levelId: Int
    ): Flow<TutorialIw>

}