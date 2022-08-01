package com.example.tz_nord_klan.data.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation


data class ContatinerWithEvent(
    @Embedded val container: Container,
    @Relation(
        entity = Event::class,
        parentColumn = "idContainer",
        entityColumn = "listEventId",
    )
    val event: List<EventWithUser>
)

data class EventWithUser(
    @Embedded val event: Event,
    @Relation(
        parentColumn = "idEvent",
        entityColumn = "idUser",
        associateBy = Junction(EventUserRef::class)
    )
    val playlists: List<User>
)


