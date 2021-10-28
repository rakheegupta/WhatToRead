package com.example.whattoread

class Article (
    val abstract:String,
    val web_url:String,
    val multimedia:Array<ArticleMultimedia>,
    val headline: HeadLine,
    val byline:Author
)

class ArticleMultimedia(
    val caption:String,
    val url:String
)

class HeadLine(val main:String)

class Author(val original:String)