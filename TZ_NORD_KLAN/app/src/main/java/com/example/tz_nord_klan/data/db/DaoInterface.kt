package com.example.tz_nord_klan.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tz_nord_klan.data.db.utils.ConvertorDate
import com.example.tz_nord_klan.data.entity.*

@Dao
@TypeConverters(ConvertorDate::class)
interface DaoInterface {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addContainer(container: Container)

    @Update
    fun updateContainer(container: Container)

    @Delete
    fun deleteContainer(container: Container)

    @Delete
    fun deleteEventUserRef(eventUserRef: EventUserRef)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addEvent(event: Event)

    @Update
    fun updateEvent(event: Event)

    @Delete
    fun deleteEvent(event: Event)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEventUserRef(st:EventUserRef)

    @Query("SELECT * FROM User WHERE nameUser=:name AND hashPassword=:password")
    fun getUser(name:String, password:String):LiveData<User>

    @Query("SELECT * FROM User")
    fun getListUser():LiveData<List<User>>

    @Transaction
    @Query("SELECT * FROM Container")
    fun getContainerWithEvent(): LiveData<List<ContatinerWithEvent>>

    @Transaction
    @Query("SELECT * FROM Container WHERE idContainer=:idContainer")
    fun getContainerWithEventById(idContainer: Long): LiveData<ContatinerWithEvent>

    @Transaction
    @Query("SELECT * FROM Container WHERE isUsed=1")
    fun getContainerWithEventByUsed(): LiveData<ContatinerWithEvent>

    @Transaction
    @Query("Update Container SET isUsed = 0 WHERE isUsed=1")
    fun setContainerWithEventByUsed()

    @Transaction
    @Query("SELECT * FROM Event")
    fun getUserWithEvent() : LiveData<List<EventWithUser>>


}