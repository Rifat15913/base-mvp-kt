package io.diaryofrifat.code.basemvp.data.local

import android.content.Context
import io.diaryofrifat.code.basemvp.data.helper.appdatabase.AppDatabase
import io.diaryofrifat.code.basemvp.data.local.model.user.UserDao
import io.diaryofrifat.code.basemvp.data.local.model.user.UserEntity
import io.reactivex.Completable

class AppLocalDataSource(context: Context) {
    /**
     * Fields
     * */
    private var mUserDao: UserDao? = null

    init {
        AppDatabase.init(context)
        mUserDao = AppDatabase.on()?.userDao()
    }

    fun insertCompletable(entity: UserEntity): Completable {
        return Completable.fromAction { mUserDao?.insert(entity) }
    }
}