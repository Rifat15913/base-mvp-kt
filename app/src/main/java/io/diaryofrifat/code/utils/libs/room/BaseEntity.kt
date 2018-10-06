package io.diaryofrifat.code.utils.libs.room

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable

import io.diaryofrifat.code.utils.helper.Constants

abstract class BaseEntity : Parcelable {
    /**
     * Fields
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constants.ColumnNames.ID)
    var id: Long = 0
}
