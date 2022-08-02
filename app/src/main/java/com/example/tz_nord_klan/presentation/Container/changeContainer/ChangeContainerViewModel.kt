package com.example.tz_nord_klan.presentation.Container.changeContainer

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.tz_nord_klan.data.db.DatabaseEvent
import com.example.tz_nord_klan.data.entity.Container
import com.example.tz_nord_klan.data.repository.RepositoryDatabaseImpl
import com.example.tz_nord_klan.domain.usecase.AddContainer
import com.example.tz_nord_klan.domain.usecase.ChangeContainer
import com.example.tz_nord_klan.domain.usecase.DeleteContainer
import com.example.tz_nord_klan.domain.usecase.SetAllContainerUsedFalse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChangeContainerViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = DatabaseEvent.getDatabaseNotes(application).daoEvent()
    private val repo = RepositoryDatabaseImpl(dao)

    fun updateContainer(container:Container){
        ChangeContainer(repo).changeContainer(container)
    }

    fun setAllContainerUsedFalse(){
        SetAllContainerUsedFalse(repo).setAllContainerUsedFalse()
    }

    fun addContainer(container:Container){
        CoroutineScope(Dispatchers.Default).launch {
            AddContainer(repo).addContainer(container)
        }
    }

    fun deleteContainer(container:Container){
        DeleteContainer(repo).deleteContainer(container)
    }
}