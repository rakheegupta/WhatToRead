package com.example.whattoread
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class ArticleViewModel :ViewModel() {

    private var mArticleRepository: ArticleRepository1 =ArticleRepository1()

    fun getArticleRepository():ArticleRepository1{
        return mArticleRepository
    }
    fun fetchArticles(query:String?,sortOrder:String, beginDate: String, funcQueryParams:Array<String>)
    {
        mArticleRepository.fetchArticles(query,sortOrder,beginDate,funcQueryParamsAsString(funcQueryParams))
    }

    private fun funcQueryParamsAsString(funcQueryParams:Array<String>):String? {
        if (funcQueryParams.isEmpty())
            return null
        var fq="news_desk:("
        for(param in funcQueryParams){
            fq = fq.plus(param)
            fq = fq.plus(" ")
        }
        fq = fq.plus(")")
        return fq
    }
}