package com.example.tz_nord_klan.presentation.adapter.Interface

import com.example.tz_nord_klan.data.entity.EventWithUser


interface InterfaceEvent {

    fun onClickEvent(event: EventWithUser)
}