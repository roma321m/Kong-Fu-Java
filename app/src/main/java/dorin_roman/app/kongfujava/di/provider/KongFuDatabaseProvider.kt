package dorin_roman.app.kongfujava.di.provider

import android.content.Context
import androidx.room.Room
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.domain.source.KongFuDataBase

object KongFuDatabaseProvider {

    private const val DATABASE_NAME = "kong_fu_db"

    fun provide(context: Context): KongFuDataBase {
        return Room.databaseBuilder(
            context,
            KongFuDataBase::class.java,
            DATABASE_NAME
        )
            .createFromAsset(context.getString(R.string.database_kong_fu_db))
            .build()
    }
}