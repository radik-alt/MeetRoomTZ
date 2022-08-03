package com.example.tz_nord_klan.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.tz_nord_klan.data.db.utils.ConvertorDate
import com.example.tz_nord_klan.data.entity.Container
import com.example.tz_nord_klan.data.entity.Event
import com.example.tz_nord_klan.data.entity.EventUserRef
import com.example.tz_nord_klan.data.entity.User

@Database(entities = [User::class, Container::class, Event::class, EventUserRef::class], version = 1, exportSchema = true)
@TypeConverters(ConvertorDate::class)
abstract class DatabaseEvent() : RoomDatabase() {

    abstract fun daoEvent (): DaoInterface

    companion object{
        @Volatile
        var INSTANCE: DatabaseEvent?= null

        fun getDatabaseNotes(context: Context): DatabaseEvent {
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val roomDataBaseInstance = Room.databaseBuilder(
                    context,
                    DatabaseEvent::class.java,
                    "Database.db")
                    .createFromAsset("database/DatabaseAssets.db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = roomDataBaseInstance
                return roomDataBaseInstance
            }
        }

    }

}