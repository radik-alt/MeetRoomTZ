package com.example.tz_nord_klan.domain.repository

import androidx.lifecycle.LiveData
import com.example.tz_nord_klan.data.entity.*

interface RepositoryDB {

    suspend fun insertContainer(container: Container)

    suspend fun insertEvent (event: Event)

    suspend fun insertUser(user: User)

    suspend fun insertEventUserRef(eventUserRef: EventUserRef)

    fun updateContainer(container: Container)

    fun deleteContainer(container: Container)

    fun deleteEventUserRef(eventUserRef: EventUserRef)

    fun updateEvent(event: Event)

    fun deleteEvent(event: Event)

    fun getContainerWithEventAndUser() : LiveData<List<ContatinerWithEvent>>

    fun getContainerWithEventAndUserById(idContainer: Long):LiveData<ContatinerWithEvent>

    fun getContainerWithEventAndUserByUsed():LiveData<ContatinerWithEvent>

    fun setContainerWithEventByUsed()

    fun getUserWithEvent() : LiveData<List<EventWithUser>>

}