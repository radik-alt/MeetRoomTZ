package com.example.tz_nord_klan.domain.usecase

import com.example.tz_nord_klan.data.entity.User
import com.example.tz_nord_klan.domain.repository.RepositoryDB

class AddUser(private val repositoryDB: RepositoryDB) {

    suspend fun addUser(user: User) {
        repositoryDB.insertUser(user)
    }

}