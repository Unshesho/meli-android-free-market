package com.meli.freemarket.di.poc.feature1.presentation

import androidx.lifecycle.ViewModel
import com.meli.freemarket.di.poc.feature1.data.KoinRepository

class KoinViewModel(private val repository: KoinRepository): ViewModel() {

    fun sayHello(name: String): String {
        val foundUser = repository.findUser(name)
        return foundUser?.let { "Hello '$it' from $this" } ?: "User '$name' not found."
    }
}
