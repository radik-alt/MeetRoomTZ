package com.example.tz_nord_klan.domain.usecase

import com.example.tz_nord_klan.data.entity.EventUserRef
import com.example.tz_nord_klan.domain.repository.RepositoryDB

class AddEventUserRef(private val repositoryDB: RepositoryDB) {

    suspend fun addEventUserRef(eventUserRef: EventUserRef) {
        repositoryDB.insertEventUserRef(eventUserRef)
    }

}