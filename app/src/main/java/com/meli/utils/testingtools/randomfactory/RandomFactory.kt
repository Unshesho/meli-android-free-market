package com.meli.utils.testingtools.randomfactory

import kotlin.random.Random

object RandomFactory {
    fun generateRandomString(): String {
        val charPool = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return (1..9)
            .map { Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("")
    }

    fun generateRandomFloat(): Float {
        return Random.nextFloat() * (1.0f - 0.0f) + 0.0f
    }
}