package com.example.tz_nord_klan.domain.usecase

import com.example.tz_nord_klan.data.entity.EventUserRef
import com.example.tz_nord_klan.domain.repository.RepositoryDB

class DeleteEventUserRef(private val repositoryDB: RepositoryDB) {

    fun deleteEventUserRef(eventUserRef: EventUserRef){
        repositoryDB.deleteEventUserRef(eventUserRef)
    }
}