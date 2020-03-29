package com.demo.trendingrepozomato.dagger

import android.util.Log
import com.demo.trendingrepozomato.utils.ApiInterface
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/**
 * Created by Ajeet Singh on 29/3/20.
 */
@Module
@Singleton
class RetrofitModule {


    /**
     * This method will provide the OkHTTPClient
     */
    @Provides
    @Singleton
    fun getOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val interceptor1 = Interceptor { chain ->
            val response = chain.proceed(chain.request())
            Log.i("DEBUG","Request Message: ${response.body.toString()}")
            Log.i("DEBUG","Request Code: ${response.code}")
            response.isSuccessful
            response

        }
        return OkHttpClient
            .Builder()
            .retryOnConnectionFailure(false)
            .addInterceptor(interceptor)
            .addInterceptor(interceptor1)
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    /**
     * This method will provide the Retrofit client
     */
    @Provides
    @Singleton
    fun getClient(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://github-trending-api.now.sh")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()
    }

    /**
     * This method will provide the Api Interface
     */
    @Provides
    @Singleton
    fun getApiInterface(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

}