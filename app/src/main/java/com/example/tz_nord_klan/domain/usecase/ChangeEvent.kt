package com.example.tz_nord_klan.domain.usecase

import com.example.tz_nord_klan.data.entity.Event
import com.example.tz_nord_klan.domain.repository.RepositoryDB

class ChangeEvent(private var repositoryDB: RepositoryDB){

    fun changeEvent(event:Event){
        repositoryDB.updateEvent(event)
    }
}