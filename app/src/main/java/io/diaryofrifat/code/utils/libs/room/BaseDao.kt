package io.diaryofrifat.code.utils.libs.room

import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Update

interface BaseDao<T> {
    /**
     * Insert a entity in the database. If the entity already exists, replace it.
     *
     * @param entity the entity to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg entity: T): LongArray

    /**
     * Insert a list of entity in the database. If the entities already exist, replace it.
     *
     * @param entityList the entity to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBulk(entityList: List<T>): LongArray

    /**
     * Update a entity in the database.
     *
     * @param entity the entity to be updated.
     */
    @Update
    fun update(vararg entity: T): Int

    /**
     * Delete a entity in the database.
     *
     * @param entity the entity to be delete.
     */
    @Delete
    fun delete(vararg entity: T)
}
