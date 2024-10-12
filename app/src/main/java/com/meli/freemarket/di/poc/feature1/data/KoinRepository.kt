package com.meli.freemarket.di.poc.feature1.data

class KoinRepository : KoinRepo {

    private val userList = arrayListOf(
        User("Pepe"),
        User("Juan"),
        User("Pollo")
    )

    override fun findUser(name: String): User? = userList.firstOrNull { it.name == name }

    override fun addUsers(users: List<User>) {
        userList.addAll(users)
    }
}