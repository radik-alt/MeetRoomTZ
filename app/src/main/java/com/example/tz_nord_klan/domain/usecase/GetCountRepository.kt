package com.example.tz_nord_klan.domain.usecase

import androidx.lifecycle.LiveData
import com.example.tz_nord_klan.domain.repository.RepositoryDB

class GetCountRepository(private val repositoryDB: RepositoryDB) {

    fun getCountRepository():LiveData<Int>{
        return repositoryDB.getCountRepository()
    }

}