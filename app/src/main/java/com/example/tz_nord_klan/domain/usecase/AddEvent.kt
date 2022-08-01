package com.example.tz_nord_klan.domain.usecase

import com.example.tz_nord_klan.data.entity.Event
import com.example.tz_nord_klan.domain.repository.RepositoryDB

class AddEvent(private val repositoryDB: RepositoryDB) {

    suspend fun addEvent (event: Event) {
        repositoryDB.insertEvent(event)
    }

}