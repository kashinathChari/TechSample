package com.designway.parko.networkService

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {

    fun observe(): Flow<Status>
    fun observeOldVersion(): Flow<Status>

    enum class Status{
        Available,Unavailable,Losing,Lost
    }
}