package com.example.tz_nord_klan.domain.usecase

import androidx.lifecycle.LiveData
import com.example.tz_nord_klan.data.entity.ContatinerWithEvent
import com.example.tz_nord_klan.domain.repository.RepositoryDB

class GetContainerByUsed(private val repositoryDB: RepositoryDB) {

    fun getContainerWithEventAndUserByUsed(): LiveData<ContatinerWithEvent> {
        return repositoryDB.getContainerWithEventAndUserByUsed()
    }
}