package com.example.tz_nord_klan.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tz_nord_klan.data.db.DatabaseEvent
import com.example.tz_nord_klan.data.entity.*
import com.example.tz_nord_klan.data.repository.RepositoryDatabaseImpl
import com.example.tz_nord_klan.domain.usecase.GetUserWithEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class viewModelDB(application: Application, private val getUserWithEvent: GetUserWithEvent) : AndroidViewModel(application) {

    private val repositoryDatabaseImpl: RepositoryDatabaseImpl

    private val authUser:MutableLiveData<User> = MutableLiveData<User>()

    init {
        val dao = DatabaseEvent.getDatabaseNotes(application).daoEvent()
        repositoryDatabaseImpl = RepositoryDatabaseImpl(dao)
    }

    fun insertUser(user: User){
        CoroutineScope(Dispatchers.Default).launch {
            repositoryDatabaseImpl.insertUser(user)
        }
    }

    fun insertEvent(event:Event){
        CoroutineScope(Dispatchers.Default).launch {
            repositoryDatabaseImpl.insertEvent(event)
        }
    }

    fun insertContainer(container: Container){
        CoroutineScope(Dispatchers.Default).launch {
            repositoryDatabaseImpl.insertContainer(container)
        }
    }

    fun insertEventWithUserRef(eventUserRef: EventUserRef) {
        CoroutineScope(Dispatchers.Default).launch {
            repositoryDatabaseImpl.insertEventUserRef(eventUserRef)
        }
    }

    fun getUser(name:String, password: String):LiveData<User>{
        return repositoryDatabaseImpl.getUser(name, password)
    }

    fun getListUser() : LiveData<List<User>>{
        return repositoryDatabaseImpl.getListUser()
    }

    fun updateContainer(container: Container){
        repositoryDatabaseImpl.updateContainer(container)
    }

    fun deleteContainer(container: Container){
        repositoryDatabaseImpl.deleteContainer(container)
    }

    fun updateEvent(event: Event){
        repositoryDatabaseImpl.updateEvent(event)
    }

    fun deleteEvent(event: Event){
        repositoryDatabaseImpl.deleteEvent(event)
    }

    fun deleteEventUserRef(eventUserRef: EventUserRef){
        repositoryDatabaseImpl.deleteEventUserRef(eventUserRef)
    }

    fun getAuthUser():LiveData<User>{
        return authUser
    }

    fun setAuthUser(user: User){
        authUser.value = user
    }

    fun getEventWithUser() : LiveData<List<EventWithUser>> = repositoryDatabaseImpl.getUserWithEvent()

    fun getContainerWithEventAndUser() : LiveData<List<ContatinerWithEvent>> = repositoryDatabaseImpl.getContainerWithEventAndUser()

    fun getContainerWithEventAndUserById(idContainer:Long) : LiveData<ContatinerWithEvent> = repositoryDatabaseImpl.getContainerWithEventAndUserById(idContainer)

    fun getContainerWithEventAndUserByUsed(): LiveData<ContatinerWithEvent> = repositoryDatabaseImpl.getContainerWithEventAndUserByUsed()

    fun setContainerWithEventByUsed() = repositoryDatabaseImpl.setContainerWithEventByUsed()
}