package dorin_roman.app.kongfujava.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dorin_roman.app.kongfujava.data.repository.ChildIdRepository.Companion.PREFERENCE_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

val Context.childIdDataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)

@ViewModelScoped
class ChildIdRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {

    companion object {
        const val PREFERENCE_NAME = "child_id_preferences"
        const val PREFERENCE_KEY = "child_id"
    }

    private object PreferenceKeys {
        val childIdKey = stringPreferencesKey(PREFERENCE_KEY)
    }

    private val dataStore = context.childIdDataStore

    suspend fun persistChildId(childId: String) {
        dataStore.edit { preference ->
            preference[PreferenceKeys.childIdKey] = childId
        }
    }

    val readChildId: Flow<String> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val childId = preferences[PreferenceKeys.childIdKey] ?: ""
            childId
        }
}