package io.diaryofrifat.code.basemvp.data.local.user

import androidx.room.Dao
import io.diaryofrifat.code.utils.libs.room.BaseDao

@Dao
interface UserDao : BaseDao<UserEntity>