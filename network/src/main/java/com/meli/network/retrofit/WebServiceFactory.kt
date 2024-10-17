package com.meli.network.retrofit

import com.meli.network.config.WebServiceConfig

class WebServiceFactory<TWebService> constructor(
    private val tClass: Class<TWebService>
) {
    fun createRemoteWebServiceConfig(): TWebService =
        RemoteWebService<TWebService>()
            .create(tClass = tClass, baseUrl = WebServiceConfig.Url.REMOTE_HOST)
}
