package com.vtpsales.vtpsales.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vtpsales.vtpsales.data.entities.User
import kotlin.coroutines.coroutineContext

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao

    companion object {
        @Volatile private var appDatabaseInstance : AppDatabase? = null

        fun getDatabase(context : Context) : AppDatabase =
            appDatabaseInstance ?: synchronized(this) {
                appDatabaseInstance ?: buildDatabase(context).also { appDatabaseInstance = it }
            }

        private fun buildDatabase(context : Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, "VTPSales").build()

    }
}