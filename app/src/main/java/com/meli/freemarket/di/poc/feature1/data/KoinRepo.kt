package com.meli.freemarket.di.poc.feature1.data

interface KoinRepo {
    fun findUser(name : String): User?
    fun addUsers(users : List<User>)
}