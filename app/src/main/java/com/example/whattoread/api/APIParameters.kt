package com.example.whattoread.api

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class APIParameters(
    val date:String,
    val sortOrder:String,
    val newsDeskValues:Array<String>,
    val searchQuery:String?
):Parcelable

