package com.example.tz_nord_klan.domain.usecase

import androidx.lifecycle.LiveData
import com.example.tz_nord_klan.data.entity.EventWithUser
import com.example.tz_nord_klan.domain.repository.RepositoryDB

class GetUserWithEvent(private val repositoryDB: RepositoryDB) {

    fun getUserWithEvent(): LiveData<List<EventWithUser>> {
        return repositoryDB.getUserWithEvent()
    }

}