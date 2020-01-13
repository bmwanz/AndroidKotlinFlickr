package com.maxscrub.bw.androidkotlinflickr

import java.lang.Exception


interface OnDataAvailable {
    fun onDataAvailable(data: List<Photo>)
    fun onError(exception: Exception)
}