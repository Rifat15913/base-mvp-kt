package io.diaryofrifat.code.utils.libs.room

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import io.reactivex.Completable

interface BaseDao<T> {
    /**
     * Insert a entity in the database. If the entity already exists, replace it.
     *
     * @param entity the entity to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg entity: T): Completable

    /**
     * Insert a list of entity in the database. If the entities already exist, replace it.
     *
     * @param entityList the entity to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBulk(entityList: List<T>): Completable

    /**
     * Update a entity in the database.
     *
     * @param entity the entity to be updated.
     */
    @Update
    fun update(vararg entity: T): Completable

    /**
     * Delete a entity in the database.
     *
     * @param entity the entity to be delete.
     */
    @Delete
    fun delete(vararg entity: T): Completable
}
