package com.example.cathaybktest.network

import com.example.cathaybktest.model.ApiResponse
import com.example.cathaybktest.model.Attraction
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Url

interface ApiService {
    @GET
    @Headers("Accept: application/json")
    fun getAttractions(@Url url: String): Call<ApiResponse>
}

