package com.meli.network.retrofit

import android.content.Context
import com.meli.network.config.Environment.Remote
import com.meli.network.config.WebServiceConfig

class WebServiceFactory<TWebService> constructor(
    private val tClass: Class<TWebService>,
    private val context: Context
) {

    fun createWebServiceInstance(environment: String): TWebService {
        return when (environment) {
            Remote.name -> createRemoteWebServiceConfig(baseUrl = WebServiceConfig.Url.REMOTE_HOST)
            else -> throw Exception(message = "Environment $environment not supported")
        }
    }

    private fun createRemoteWebServiceConfig(baseUrl: String): TWebService =
        RemoteWebService<TWebService>()
            .create(tClass = tClass, baseUrl = baseUrl)
}
