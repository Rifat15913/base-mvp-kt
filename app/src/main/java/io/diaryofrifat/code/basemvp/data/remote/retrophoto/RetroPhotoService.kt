package io.diaryofrifat.code.basemvp.data.remote.retrophoto

import io.reactivex.Flowable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetroPhotoService {
    @GET("/photos")
    fun getAllPhotos(): Flowable<List<RetroPhoto>>

    companion object {
        private val sRetrofitBuilder = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build()

        private var sInstance: RetroPhotoService? = null

        fun on(): RetroPhotoService{
            if(sInstance == null){
                sInstance = sRetrofitBuilder.create(RetroPhotoService::class.java)
            }
            
            return sInstance!!
        }
    }
}