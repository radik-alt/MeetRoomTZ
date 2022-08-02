package com.example.tz_nord_klan.domain.usecase

import com.example.tz_nord_klan.data.entity.Container
import com.example.tz_nord_klan.domain.repository.RepositoryDB

class DeleteContainer(private val repositoryDB: RepositoryDB) {

    fun deleteContainer(container:Container){
        repositoryDB.deleteContainer(container)
    }

}