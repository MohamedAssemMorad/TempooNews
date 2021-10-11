package com.example.newproject.data.network

import androidx.lifecycle.MutableLiveData
import com.example.newproject.data.model.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object NewsAPIController {

    fun getNewsList(q: String?): MutableLiveData<NewsResponse> {

        val data = MutableLiveData<NewsResponse>()

        if (getAppService() != null) {
            getAppService()?.callGetAllNews(q ?: "")
                ?.enqueue(object : Callback<NewsResponse?> {
                    override fun onResponse(
                        call: Call<NewsResponse?>,
                        response: Response<NewsResponse?>
                    ) {
                        if (response.isSuccessful && response.body() != null)
                            data.value = response.body()
                        else
                            data.value = null
                    }

                    override fun onFailure(call: Call<NewsResponse?>, t: Throwable) {
                        data.value = null
                    }
                })
        }

        return data
    }

    private fun getAppService(): RetrofitService? {
        return RetrofitHelper.getInstance()
    }
}