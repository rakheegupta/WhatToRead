package com.example.whattoread

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleRepository1 {
    private val mArticles = MutableLiveData<MutableList<Article>>()

    fun getArticles(): LiveData<MutableList<Article>> {
        return mArticles
    }

    fun fetchArticles(sortOrder:String,beginDate:String,funcQueryParams:String?)
    {
        val apiService = ArticleService()
        val call: Call<NYTApiResponse> = apiService.getArticlesFromNYT()
        /*if (funcQueryParams == null)
            apiService.getArticlesFromNYT(sortOrder, beginDate)
        else
            apiService.getArticlesFromNYT(sortOrder, beginDate, funcQueryParams)
        */
        call.enqueue(object : Callback<NYTApiResponse>
        {
            override fun onResponse(
                call: Call<NYTApiResponse>,
                response: Response<NYTApiResponse>
            ) {
                val apiResponse: NYTApiResponse? = response.body()
                if(apiResponse != null){
                    mArticles.value = apiResponse.response.docs
                    Log.i("API", "onResponse: ${apiResponse.response.docs.size}")
                }
                else
                    Log.i("API","No Results")
                if(response.errorBody() != null)
                    Log.i("API","error body: ${response.errorBody().toString()}")
            }

            override fun onFailure(call: Call<NYTApiResponse>, t: Throwable) {
                Log.i("NYT API: ","Faliure")
            }
        })
    }
}
