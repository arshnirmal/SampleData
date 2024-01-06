package com.example.sampledata.network

import com.example.sampledata.model.ApiResponse
import com.example.sampledata.model.Item
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("7181ed68-194b-4509-9889-7513e29345d4")
    suspend fun getItemsList(): ApiResponse

    companion object{
        private var apiService: ApiService? = null
        fun getInstance(): ApiService{
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://run.mocky.io/v3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiService::class.java)
            }
            return apiService!!
        }
    }
}