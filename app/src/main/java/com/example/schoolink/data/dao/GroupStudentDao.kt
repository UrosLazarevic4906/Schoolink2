package com.example.schoolink.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.schoolink.data.entities.refference.GroupStudentCrossRef

@Dao
interface GroupStudentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGroupStudentCrossRef(crossRef: GroupStudentCrossRef)
}