package io.diaryofrifat.code.basemvp.data.local.model.user

import android.arch.persistence.room.Dao
import io.diaryofrifat.code.utils.libs.room.BaseDao

@Dao
interface UserDao : BaseDao<UserEntity>