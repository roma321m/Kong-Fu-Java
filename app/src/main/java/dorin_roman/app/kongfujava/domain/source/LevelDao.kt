package dorin_roman.app.kongfujava.domain.source

import androidx.room.Dao
import androidx.room.Query
import dorin_roman.app.kongfujava.domain.models.Answer
import dorin_roman.app.kongfujava.domain.models.Level
import dorin_roman.app.kongfujava.domain.models.Question
import kotlinx.coroutines.flow.Flow

@Dao
interface LevelDao {
    companion object{
        const val DATABASE_LEVEL_TABLE = "level"
        const val DATABASE_QUESTION_TABLE = "question"
        const val DATABASE_ANSWER_TABLE = "answer"
    }


    @Query("SELECT * FROM level WHERE worldId=:worldId")
    fun getAllLevels(
        worldId : Int
    ): Flow<List<Level>>

    @Query("SELECT * FROM question WHERE id=:id")
    fun getQuestion(
        id: Int
    ): Flow<Question>

    @Query("SELECT * FROM answer WHERE levelId=:levelId")
    fun getAnswer(
        levelId: Int
    ): Flow<Answer>

}