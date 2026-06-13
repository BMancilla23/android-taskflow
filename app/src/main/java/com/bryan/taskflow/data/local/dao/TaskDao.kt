package com.bryan.taskflow.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.bryan.taskflow.data.local.entity.TaskEntity
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
   @Query(
       """
           SELECT * FROM tasks WHERE userId = :userId ORDER BY id DESC
       """
   )
   fun getTaskByUser(
       userId: Long
   ): Flow<List<TaskEntity>>

   @Query(
       """
           SELECT * FROM tasks WHERE id =:taskId
       """
   )
   suspend fun getById(
       taskId: Long
   ): TaskEntity?


   @Insert
   suspend fun insert(
       task: TaskEntity
   )

   @Update
   suspend fun update(
       task: TaskEntity
   )

   @Delete
   suspend fun delete(
       task: TaskEntity
   )
}