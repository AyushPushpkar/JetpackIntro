package com.example.jetpackintro

interface Paginator<Key , item> {                     //to define behaviors & properties
    suspend fun loadNextItems()               // by  default abstract
    fun reset()
}