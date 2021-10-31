package com.vtpsales.vtpsales.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vtpsales.vtpsales.data.entities.User

@Dao
interface UserDao {
    @Query("Select * from User")
    fun getUser() : LiveData<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user : User)
}