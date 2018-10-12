package io.diaryofrifat.code.utils.libs.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.*

abstract class BaseDatabase : RoomDatabase() {
    companion object {
        /**
         * This method creates a room database
         *
         * @param context application context
         * @param databaseName name of the database
         * @param databaseService service class for database
         * @param migrationScripts migration scripts
         * @return [T] database
         * */
        fun <T : RoomDatabase> createDb(context: Context, databaseName: String,
                                        databaseService: Class<T>,
                                        vararg migrationScripts: String): T {
            val builder = Room.databaseBuilder(context, databaseService, databaseName)

            for (migration in getMigrations(*migrationScripts)) {
                builder.addMigrations(migration)
            }

            return builder.build()
        }

        private fun getMigrations(vararg migrationScripts: String): List<Migration> {
            val migrationList = ArrayList<Migration>()

            var startVersion = 1
            var endVersion = 2

            var migration: Migration

            for (migrationSchema in migrationScripts) {
                migration = object : Migration(startVersion, endVersion) {
                    override fun migrate(database: SupportSQLiteDatabase) {
                        database.execSQL(migrationSchema)
                    }
                }

                startVersion++
                endVersion++

                migrationList.add(migration)
            }

            return migrationList
        }
    }
}
