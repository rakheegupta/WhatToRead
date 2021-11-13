package com.example.whattoread

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


//https://api.nytimes.com/svc/search/v2/
// articlesearch.json?
// begin_date=20160112&
// sort=oldest&
//  fq=news_desk:(%22Education%22%20%22Health%22)&
//  api-key=VSqRE2UoTOiWA989w3qZzn0cki6TptBv

const val API_KEY:String = "VSqRE2UoTOiWA989w3qZzn0cki6TptBv"
const val BASE_URL:String ="https://api.nytimes.com/svc/search/v2/"

interface ArticleService{

    @GET("articlesearch.json")
    fun getArticlesFromNYT():Call<NYTApiResponse>

    @GET("articlesearch.json")
    fun getArticlesFromNYT(@Query("q") query:String?,
                           @Query("sort") sortOrder:String,
                           @Query("begin_date") beginDate:String,
                           @Query("fq") funcQueryParams:String?)
    : Call<NYTApiResponse>


    companion object {
        operator fun invoke():ArticleService{
            val requestInterceptor = Interceptor{chain->
                val url:HttpUrl = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("api-key", API_KEY)
                    .build()
                val newRequest: Request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor chain.proceed(newRequest)
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()

            return Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ArticleService::class.java)
        }
    }
}