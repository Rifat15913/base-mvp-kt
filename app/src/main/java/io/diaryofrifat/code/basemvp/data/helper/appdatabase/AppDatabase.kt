package io.diaryofrifat.code.basemvp.data.helper.appdatabase

import android.content.Context
import androidx.room.Database

import io.diaryofrifat.code.basemvp.R
import io.diaryofrifat.code.basemvp.data.local.model.user.UserDao
import io.diaryofrifat.code.basemvp.data.local.model.user.UserEntity
import io.diaryofrifat.code.utils.helper.DataUtils
import io.diaryofrifat.code.utils.libs.room.BaseDatabase

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : BaseDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var sInstance: AppDatabase? = null

        // Get a database instance
        @Synchronized
        fun on(): AppDatabase? {
            return sInstance
        }

        fun init(context: Context) {
            synchronized(AppDatabase::class.java) {
                sInstance = createDb(context,
                        DataUtils.getString(R.string.app_name), AppDatabase::class.java)
            }
        }
    }
}
