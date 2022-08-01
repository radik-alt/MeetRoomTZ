package com.example.tz_nord_klan.presentation.Event

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tz_nord_klan.data.entity.Container
import com.example.tz_nord_klan.data.entity.Event
import com.example.tz_nord_klan.data.entity.EventWithUser

class SharedEventViewModel : ViewModel() {

    private var eventObject = MutableLiveData<EventWithUser>()
    private var eventIsEdit = MutableLiveData<Boolean>()
    private var container = MutableLiveData<Container>()

    fun setIsEdit(edit: Boolean){
        eventIsEdit.value = edit
    }

    fun setContainer(containerParam: Container){
        container.value = containerParam
    }

    fun getContainer():LiveData<Container> = container

    fun getIsEdit():MutableLiveData<Boolean> = eventIsEdit

    fun getEvent() : MutableLiveData<EventWithUser> = eventObject

    fun setEvent(event: EventWithUser){
        eventObject.value = event
    }

}