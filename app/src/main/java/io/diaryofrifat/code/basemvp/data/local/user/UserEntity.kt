package io.diaryofrifat.code.basemvp.data.local.user

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import io.diaryofrifat.code.utils.helper.Constants
import io.diaryofrifat.code.utils.libs.room.BaseEntity

@Entity(tableName = Constants.TableNames.USER,
        indices = [Index(value = [Constants.ColumnNames.USER_ID], unique = true)])
data class UserEntity(
        @ColumnInfo(name = Constants.ColumnNames.USER_NAME)
        val name: String,
        @ColumnInfo(name = Constants.ColumnNames.USER_ID)
        val userId: String) : BaseEntity() {

    /**
     * Below codes are to make the object parcelable
     * */
    constructor(parcel: Parcel) : this(parcel.readString()!!, parcel.readString()!!)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(userId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserEntity> {
        override fun createFromParcel(parcel: Parcel): UserEntity {
            return UserEntity(parcel)
        }

        override fun newArray(size: Int): Array<UserEntity?> {
            return arrayOfNulls(size)
        }
    }
}