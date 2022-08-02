package com.example.tz_nord_klan.domain.usecase

import com.example.tz_nord_klan.data.entity.Event
import com.example.tz_nord_klan.domain.repository.RepositoryDB

class DeleteEvent(private val repositoryDB: RepositoryDB) {

    fun deleteEvent(event:Event){
        repositoryDB.deleteEvent(event)
    }

}