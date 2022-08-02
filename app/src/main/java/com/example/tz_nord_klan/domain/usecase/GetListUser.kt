package com.example.tz_nord_klan.domain.usecase

import androidx.lifecycle.LiveData
import com.example.tz_nord_klan.data.entity.User
import com.example.tz_nord_klan.domain.repository.RepositoryDB

class GetListUser(private val repositoryDB: RepositoryDB) {

    fun getUserList(): LiveData<List<User>> {
        return repositoryDB.getListUser()
    }
}