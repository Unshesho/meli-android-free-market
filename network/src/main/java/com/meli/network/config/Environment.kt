package com.meli.network.config

sealed class Environment(val name: String) {
    object Remote: Environment("remote")
}
