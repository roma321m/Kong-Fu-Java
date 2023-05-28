package dorin_roman.app.kongfujava.data.repository

import android.content.Context
import dorin_roman.app.kongfujava.di.provider.KongFuDatabaseProvider.DATABASE_NAME
import dorin_roman.app.kongfujava.domain.repository.DeleteDB
import javax.inject.Inject

class DeleteDBImpl @Inject constructor(
    private val context: Context
) : DeleteDB {

    override fun launch() {
        context.deleteDatabase(DATABASE_NAME)
    }

}