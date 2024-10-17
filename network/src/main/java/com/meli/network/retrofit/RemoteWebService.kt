package com.meli.network.retrofit

import com.meli.network.config.WebServiceConfig
import java.util.concurrent.TimeUnit
import javax.net.SocketFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteWebService<TRetrofitWebService> {

    fun create(
        tClass: Class<TRetrofitWebService>,
        baseUrl: String
    ): TRetrofitWebService {
        val okHttpClient = makeOkHttpClient(makeLoggingInterceptor())

        return createRetrofit(
            tClass = tClass,
            baseUrl = baseUrl,
            okHttpClient = okHttpClient
        )
    }

    private fun makeOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .socketFactory(SocketFactory.getDefault())
        .connectTimeout(WebServiceConfig.Timeout.CONNECTION_TIME, TimeUnit.SECONDS)
        .build()

    private fun makeLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    private fun createRetrofit(
        okHttpClient: OkHttpClient,
        tClass: Class<TRetrofitWebService>,
        baseUrl: String
    ): TRetrofitWebService =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(tClass)
}