package com.example.tz_nord_klan.presentation.Event.changeEvent

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.tz_nord_klan.data.db.DatabaseEvent
import com.example.tz_nord_klan.data.entity.Event
import com.example.tz_nord_klan.data.entity.EventUserRef
import com.example.tz_nord_klan.data.repository.RepositoryDatabaseImpl
import com.example.tz_nord_klan.domain.usecase.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChangeEventViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = DatabaseEvent.getDatabaseNotes(application).daoEvent()
    private val repo = RepositoryDatabaseImpl(dao)

    fun updateEvent(event:Event){
        ChangeEvent(repo).changeEvent(event)
    }

    fun addEvent(event: Event){
        CoroutineScope(Dispatchers.Default).launch {
            AddEvent(repo).addEvent(event)
        }
    }

    fun deleteEvent(event: Event){
        DeleteEvent(repo).deleteEvent(event)
    }

    fun addUserToEvent(eventUserRef: EventUserRef){
        CoroutineScope(Dispatchers.Default).launch {
            AddEventUserRef(repo).addEventUserRef(eventUserRef)
        }
    }

    fun deleteUserToEvent(eventUserRef: EventUserRef){
        DeleteEventUserRef(repo).deleteEventUserRef(eventUserRef)
    }

}