package com.example.tz_nord_klan.data.repository

import androidx.lifecycle.LiveData
import com.example.tz_nord_klan.data.db.DaoInterface
import com.example.tz_nord_klan.data.entity.*
import com.example.tz_nord_klan.domain.repository.RepositoryDB

class RepositoryDatabaseImpl(private val daoInterface: DaoInterface) : RepositoryDB {


    fun getUser(name: String, password: String): LiveData<User> =
        daoInterface.getUser(name, password)

    fun getListUser(): LiveData<List<User>> = daoInterface.getListUser()

    fun getCountContainer() : LiveData<Int> = daoInterface.getCountContainer()

    override suspend fun insertContainer(container: Container) {
        daoInterface.addContainer(container)
    }

    override suspend fun insertEvent(event: Event) {
        daoInterface.addEvent(event)
    }

    override suspend fun insertUser(user: User) {
        daoInterface.addUser(user)
    }

    override suspend fun insertEventUserRef(eventUserRef: EventUserRef) {
        daoInterface.insertEventUserRef(eventUserRef)
    }

    override fun updateContainer(container: Container) {
        daoInterface.updateContainer(container)
    }

    override fun deleteContainer(container: Container) {
        daoInterface.deleteContainer(container)
    }

    override fun deleteEventUserRef(eventUserRef: EventUserRef) {
        daoInterface.deleteEventUserRef(eventUserRef)
    }

    override fun updateEvent(event: Event) {
        daoInterface.updateEvent(event)
    }

    override fun deleteEvent(event: Event) {
        daoInterface.deleteEvent(event)
    }

    override fun getContainerWithEventAndUser(): LiveData<List<ContatinerWithEvent>> {
        return daoInterface.getContainerWithEvent()
    }

    override fun getContainerWithEventAndUserById(idContainer: Long): LiveData<ContatinerWithEvent> {
        return daoInterface.getContainerWithEventById(idContainer)
    }

    override fun getContainerWithEventAndUserByUsed(): LiveData<ContatinerWithEvent> {
        return daoInterface.getContainerWithEventByUsed()
    }

    override fun setContainerWithEventByUsed() {
        daoInterface.setContainerWithEventByUsed()
    }


    override fun getUserWithEvent(): LiveData<List<EventWithUser>> {
        return daoInterface.getUserWithEvent()
    }

}