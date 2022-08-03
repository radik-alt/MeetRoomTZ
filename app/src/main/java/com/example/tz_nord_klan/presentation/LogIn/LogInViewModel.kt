package com.example.tz_nord_klan.presentation.LogIn

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.tz_nord_klan.data.db.DatabaseEvent
import com.example.tz_nord_klan.data.entity.User
import com.example.tz_nord_klan.data.repository.RepositoryDatabaseImpl
import com.example.tz_nord_klan.domain.usecase.GetListUser

class LogInViewModel(
    application: Application

) : AndroidViewModel(application) {

    private val dao = DatabaseEvent.getDatabaseNotes(application).daoEvent()
    private val repo = RepositoryDatabaseImpl(dao)


    fun getListUser():LiveData<List<User>>{
        return GetListUser(repo).getUserList()
    }




}