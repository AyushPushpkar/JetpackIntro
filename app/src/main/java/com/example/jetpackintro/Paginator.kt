package com.example.jetpackintro

interface Paginator<item> {                     //to define behaviors & properties
    fun loadNextItems()               // by  default abstract
    fun reset()
}