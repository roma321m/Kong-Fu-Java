package dorin_roman.app.kongfujava.data.repository

import dagger.hilt.android.scopes.ViewModelScoped
import dorin_roman.app.kongfujava.domain.models.levels.Answer
import dorin_roman.app.kongfujava.domain.models.levels.Level
import dorin_roman.app.kongfujava.domain.models.levels.Question
import dorin_roman.app.kongfujava.domain.models.levels.Tutorial
import dorin_roman.app.kongfujava.domain.source.LevelDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class LevelRepository @Inject constructor(
    private val levelDao: LevelDao
) {
    fun getAllLevels(worldId: Int):Flow<List<Level>>{
        return levelDao.getAllLevels(worldId)
    }

    fun getLevel(levelId: Int): Flow<Level> {
        return levelDao.getLevel(levelId)
    }

    fun getAnswer(levelId: Int): Flow<Answer> {
        return levelDao.getAnswer(levelId)
    }

    fun getTutorial(levelId: Int): Flow<Tutorial> {
        return levelDao.getTutorial(levelId)
    }

    fun getQuestion(id: Int): Flow<Question> {
        return levelDao.getQuestion(id)
    }

    fun updateScore(id: Int, score: Int) {
        levelDao.updateScore(id, score)
    }

    fun updateState(id: Int, state: Int) {
        levelDao.updateState(id, state)
    }

}