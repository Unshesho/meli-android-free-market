package com.meli.network.config

object WebServiceConfig {
    object Url {
        const val REMOTE_HOST = "https://api.mercadolibre.com/sites/MLC/"
    }

    object Timeout {
        const val CONNECTION_TIME: Long = 60
    }
}
