package com.example.tz_nord_klan.domain.usecase

import com.example.tz_nord_klan.data.entity.Container
import com.example.tz_nord_klan.domain.repository.RepositoryDB

class ChangeContainer(private val repositoryDB: RepositoryDB) {

    fun changeContainer(container:Container){
        repositoryDB.updateContainer(container)
    }
}