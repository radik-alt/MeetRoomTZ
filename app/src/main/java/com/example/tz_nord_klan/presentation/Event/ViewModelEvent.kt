package com.example.tz_nord_klan.presentation.Event

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.tz_nord_klan.data.db.DatabaseEvent
import com.example.tz_nord_klan.data.entity.ContatinerWithEvent
import com.example.tz_nord_klan.data.repository.RepositoryDatabaseImpl
import com.example.tz_nord_klan.domain.usecase.GetContainerByUsed
import com.example.tz_nord_klan.domain.usecase.GetCountRepository

class ViewModelEvent(application: Application) : AndroidViewModel(application) {

    private val dao = DatabaseEvent.getDatabaseNotes(application).daoEvent()
    private val repo = RepositoryDatabaseImpl(dao)

    fun getByUsedContainer():LiveData<ContatinerWithEvent>{
        return GetContainerByUsed(repo).getContainerWithEventAndUserByUsed()
    }

    fun getCountContainer():LiveData<Int>{
        return GetCountRepository(repo).getCountRepository()
    }

}