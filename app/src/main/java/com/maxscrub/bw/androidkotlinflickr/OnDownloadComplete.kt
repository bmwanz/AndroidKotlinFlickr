package com.maxscrub.bw.androidkotlinflickr


interface OnDownloadComplete {
    fun onDownloadComplete(data: String, status: DownloadStatus)
}