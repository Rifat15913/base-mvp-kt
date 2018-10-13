package io.diaryofrifat.code.utils.libs.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import io.diaryofrifat.code.utils.helper.Constants

abstract class BaseEntity : Parcelable {
    /**
     * Fields
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constants.ColumnNames.ID)
    var id: Long = 0
}
