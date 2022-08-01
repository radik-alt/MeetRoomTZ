package com.example.tz_nord_klan.domain.usecase

import com.example.tz_nord_klan.data.entity.Container
import com.example.tz_nord_klan.domain.repository.RepositoryDB

class AddContainer(private val repositoryDB: RepositoryDB) {

    suspend fun addContainer(container: Container){
        repositoryDB.insertContainer(container)
    }

}