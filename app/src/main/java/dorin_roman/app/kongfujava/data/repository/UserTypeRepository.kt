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
import dorin_roman.app.kongfujava.data.models.UserType
import dorin_roman.app.kongfujava.data.repository.UserTypeRepository.Companion.PREFERENCE_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

val Context.userTypeDataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)

@ViewModelScoped
class UserTypeRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {

    companion object {
        const val PREFERENCE_NAME = "user_type_preferences"
        const val PREFERENCE_KEY = "user_type"
    }

    private object PreferenceKeys {
        val userTypeKey = stringPreferencesKey(PREFERENCE_KEY)
    }

    private val dataStore = context.userTypeDataStore

    suspend fun persistUserType(userType: UserType) {
        dataStore.edit { preference ->
            preference[PreferenceKeys.userTypeKey] = userType.name
        }
    }

    val readUserType: Flow<String> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val userType = preferences[PreferenceKeys.userTypeKey] ?: UserType.None.name
            userType
        }
}