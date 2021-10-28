package com.example.whattoread

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
class APIParameters(
    val date:String,
    val sortOrder:String,
    val newsDeskValues:Array<String>
):Parcelable

