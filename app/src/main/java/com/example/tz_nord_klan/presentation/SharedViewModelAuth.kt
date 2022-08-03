package com.example.tz_nord_klan.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tz_nord_klan.data.db.DatabaseEvent
import com.example.tz_nord_klan.data.entity.*
import com.example.tz_nord_klan.data.repository.RepositoryDatabaseImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SharedViewModelAuth(application: Application) : AndroidViewModel(application) {

    private val authUser:MutableLiveData<User> = MutableLiveData<User>()

    fun getAuthUser():LiveData<User>{
        return authUser
    }

    fun setAuthUser(user: User){
        authUser.value = user
    }


}