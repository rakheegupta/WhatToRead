package com.example.whattoread

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Article (
    val abstract:String,
    val web_url:String,
    val multimedia:Array<ArticleMultimedia>,
    val headline: HeadLine,
    val byline:Author
) : Parcelable

@Parcelize
class ArticleMultimedia(
    val caption:String?,
    val url:String?
):Parcelable

@Parcelize
class HeadLine(val main:String):Parcelable

@Parcelize
class Author(val original:String?):Parcelable