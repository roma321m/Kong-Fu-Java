package dorin_roman.app.kongfujava.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dorin_roman.app.kongfujava.data.repository.MusicRepository.Companion.PREFERENCE_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

val Context.musicDataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)

@ViewModelScoped
class MusicRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {

    companion object {
        const val PREFERENCE_NAME = "music_preferences"
        const val PREFERENCE_KEY = "is_active"
    }

    private object PreferenceKeys {
        val isActiveKey = booleanPreferencesKey(PREFERENCE_KEY)
    }

    private val dataStore = context.musicDataStore

    suspend fun persistMusic(isActive: Boolean) {
        dataStore.edit { preference ->
            preference[PreferenceKeys.isActiveKey] = isActive
        }
    }

    val readIsActive: Flow<Boolean> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val isActive = preferences[PreferenceKeys.isActiveKey] ?: true
            isActive
        }
}