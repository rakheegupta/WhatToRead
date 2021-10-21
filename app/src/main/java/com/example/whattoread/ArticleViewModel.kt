package com.example.whattoread

import androidx.lifecycle.ViewModel

class ArticleViewModel:ViewModel() {

    private val mArticleRepository:ArticleRepository

    init{
        mArticleRepository=ArticleRepository()
    }
}