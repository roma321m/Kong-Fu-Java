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

    @Query("SELECT * FROM level WHERE id=:id")
    fun getLevel(
        id: Int
    ): Flow<Level>

    @Query("SELECT * FROM question WHERE id=:id")
    fun getQuestion(
        id: Int
    ): Flow<Question>

    @Query("SELECT * FROM answer WHERE levelId=:levelId")
    fun getAnswer(
        levelId: Int
    ): Flow<Answer>

//    @Query("SELECT * FROM level WHERE id=:id")
//    @Update
//    fun updateLevel(
//        level : Level
//    )

    @Query("UPDATE level SET score = :score WHERE id =:id")
    fun updateScore(id: Int, score: Int)

    @Query("UPDATE level SET state = :state WHERE id =:id")
    fun updateState(id: Int, state: Int)
}