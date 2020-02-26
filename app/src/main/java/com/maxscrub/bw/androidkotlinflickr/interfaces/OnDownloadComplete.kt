package com.maxscrub.bw.androidkotlinflickr.interfaces

import com.maxscrub.bw.androidkotlinflickr.task.DownloadStatus


interface OnDownloadComplete {
    fun onDownloadComplete(data: String, status: DownloadStatus)
}