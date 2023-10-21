package com.example.cathaybktest.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cathaybktest.model.ApiResponse
import com.example.cathaybktest.model.Attraction
import com.example.cathaybktest.network.ApiService
import com.google.android.gms.common.api.ApiException
import com.google.gson.Gson
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//
class AttractionViewModel : ViewModel() {
    var TAG  = "AttractionViewModel"
    val attractionsLiveData = MutableLiveData<List<Attraction>>() // 使用 List<Attraction> 保存數據

    /**
     *
     * @param page
     * @param lang
     */
    fun getAttractions(page: Int, lang: String) {
        val baseUrl = "https://www.travel.taipei/open-api/zh-tw/Attractions/All/"
        val url = "$baseUrl?page=$page&lang=$lang"

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(Gson())) // 使用 Gson 轉換
            .build()

        val apiService = retrofit.create(ApiService::class.java)
        val call = apiService.getAttractions(url)

        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    if (apiResponse != null) {
                        val attractions = apiResponse.data
                        attractionsLiveData.value = attractions
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.d(TAG,t.message.toString())

            }
        })

    }


}

