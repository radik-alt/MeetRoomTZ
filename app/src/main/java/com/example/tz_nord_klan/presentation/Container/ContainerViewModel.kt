package com.example.tz_nord_klan.presentation.Container

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.tz_nord_klan.data.db.DatabaseEvent
import com.example.tz_nord_klan.data.entity.ContatinerWithEvent
import com.example.tz_nord_klan.data.repository.RepositoryDatabaseImpl
import com.example.tz_nord_klan.domain.usecase.GetContainerWithEventAndUser

class ContainerViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = DatabaseEvent.getDatabaseNotes(application).daoEvent()
    private val repo = RepositoryDatabaseImpl(dao)

    fun getContainer():LiveData<List<ContatinerWithEvent>>{
        return GetContainerWithEventAndUser(repo).getContainerWithEventAndUser()
    }
}