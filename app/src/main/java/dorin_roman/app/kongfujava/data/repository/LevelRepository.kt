package dorin_roman.app.kongfujava.data.repository

import dagger.hilt.android.scopes.ViewModelScoped
import dorin_roman.app.kongfujava.domain.models.Answer
import dorin_roman.app.kongfujava.domain.models.Level
import dorin_roman.app.kongfujava.domain.models.Question
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

    fun getAnswer(levelId: Int): Flow<Answer> {
        return levelDao.getAnswer(levelId)
    }

    fun getQuestion(id: Int): Flow<Question> {
        return levelDao.getQuestion(id)
    }

}