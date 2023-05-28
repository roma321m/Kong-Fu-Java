package dorin_roman.app.kongfujava.data.repository

import android.content.Context
import android.util.LayoutDirection
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dorin_roman.app.kongfujava.domain.models.levels.Level
import dorin_roman.app.kongfujava.domain.models.levels.answer.Answer
import dorin_roman.app.kongfujava.domain.models.levels.question.Question
import dorin_roman.app.kongfujava.domain.models.levels.tutorial.Tutorial
import dorin_roman.app.kongfujava.domain.source.LevelDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class LevelRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val levelDao: LevelDao
) {
    fun getAllLevels(worldId: Int): Flow<List<Level>> {
        return levelDao.getAllLevels(worldId)
    }

    fun getLevel(levelId: Int): Flow<Level> {
        return levelDao.getLevel(levelId)
    }

    fun getAnswer(levelId: Int): Flow<Answer> {
        return if (context.resources.configuration.layoutDirection == LayoutDirection.RTL) {
            levelDao.getAnswerIw(levelId)
        } else {
            levelDao.getAnswer(levelId)
        }
    }

    fun getTutorial(levelId: Int): Flow<Tutorial> {
        return if (context.resources.configuration.layoutDirection == LayoutDirection.RTL) {
            levelDao.getTutorialIw(levelId)
        } else {
            levelDao.getTutorial(levelId)
        }
    }

    fun getQuestion(id: Int): Flow<Question> {
        return if (context.resources.configuration.layoutDirection == LayoutDirection.RTL) {
            levelDao.getQuestionIw(id)
        } else {
            levelDao.getQuestion(id)
        }
    }

    fun updateLevelScore(id: Int, score: Int) {
        levelDao.updateLevelScore(id, score)
    }

    fun updateLevelState(id: Int, state: Int) {
        levelDao.updateLevelState(id, state)
    }

}