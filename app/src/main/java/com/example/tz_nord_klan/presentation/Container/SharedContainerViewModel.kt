package com.example.tz_nord_klan.presentation.Container

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tz_nord_klan.data.entity.ContatinerWithEvent

class SharedContainerViewModel : ViewModel() {

    private val containerData = MutableLiveData<ContatinerWithEvent>()
    private val isEdit = MutableLiveData<Boolean>()

    fun getContainer():LiveData<ContatinerWithEvent> = containerData

    fun setContainer(container: ContatinerWithEvent){
        containerData.value = container
    }

    fun setEdit(edit: Boolean) {
        isEdit.value = edit
    }

    fun getEdit() : LiveData<Boolean> = isEdit


}