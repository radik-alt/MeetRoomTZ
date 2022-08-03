package com.example.tz_nord_klan.domain.usecase

import com.example.tz_nord_klan.domain.repository.RepositoryDB

class SetAllContainerUsedFalse(private val repositoryDB: RepositoryDB) {

    fun setAllContainerUsedFalse(){
        repositoryDB.setContainerWithEventByUsed()
    }
}