package com.example.whattoread

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ArticleRepository {
    val mArticles = MutableLiveData<List<Article>>()

    init{
        fetchArticles()
    }

    fun getArticles():LiveData<List<Article>>{
        return mArticles
    }

    fun fetchArticles()
    {

    }
}