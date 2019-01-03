package io.diaryofrifat.code.basemvp.data

import android.content.Context
import io.diaryofrifat.code.basemvp.data.local.AppLocalDataSource
import io.diaryofrifat.code.basemvp.data.local.user.UserEntity
import io.diaryofrifat.code.basemvp.data.remote.AppRemoteDataSource
import io.diaryofrifat.code.basemvp.data.remote.retrophoto.RetroPhoto
import io.reactivex.Completable
import io.reactivex.Flowable

class BaseRepository(context: Context) {
    /**
     * Fields
     * */
    private val mAppLocalDataSource = AppLocalDataSource(context)
    private val mAppRemoteDataSource = AppRemoteDataSource(context)

    companion object {
        private lateinit var sInstance: BaseRepository

        fun on(): BaseRepository {
            return sInstance
        }

        @Synchronized
        fun init(context: Context) {
            synchronized(BaseRepository::class.java) {
                sInstance = BaseRepository(context)
            }
        }
    }

    fun insertUserToDatabase(entity: UserEntity): Completable {
        return mAppLocalDataSource.insertCompletable(entity)
    }

    fun getAllPhotosFromServer(): Flowable<List<RetroPhoto>> {
        return mAppRemoteDataSource.getAllPhotos()
    }
}
