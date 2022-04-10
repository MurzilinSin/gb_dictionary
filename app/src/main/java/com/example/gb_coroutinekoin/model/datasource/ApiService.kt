package com.example.gb_coroutinekoin.model.datasource

import com.example.gb_coroutinekoin.model.data.DataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("words/search")
    suspend fun search(@Query("search") wordToSearch: String): List<DataModel>
}