package com.maxscrub.bw.androidkotlinflickr.interfaces

import com.maxscrub.bw.androidkotlinflickr.model.Photo
import java.lang.Exception


interface OnDataAvailable {
    fun onDataAvailable(data: List<Photo>)
    fun onError(exception: Exception)
}