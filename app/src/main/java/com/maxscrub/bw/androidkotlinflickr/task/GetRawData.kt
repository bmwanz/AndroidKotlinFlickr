package com.maxscrub.bw.androidkotlinflickr.task

import android.os.AsyncTask
import com.maxscrub.bw.androidkotlinflickr.interfaces.OnDownloadComplete
import timber.log.Timber
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL

enum class DownloadStatus {
    OK, IDLE, NOT_INITIALIZED, FAILED_OR_EMPTY, PERMISSIONS_ERROR, ERROR
}

class GetRawData(private val listener: OnDownloadComplete) : AsyncTask<String, Void, String>() {

    private var downloadStatus =
        DownloadStatus.IDLE

    override fun onPostExecute(result: String) {
        super.onPostExecute(result)
//        Timber.d("GetRawData.onPostExecute \n${result}")
        Timber.d("GetRawData.onPostExecute")
        listener.onDownloadComplete(result, downloadStatus)
    }

    override fun doInBackground(vararg params: String?): String {
        if (params[0] == null) {
            downloadStatus =
                DownloadStatus.NOT_INITIALIZED
            Timber.e("GetRawData.doInBackground NOT_INITIALIZED")
            return "No URL specified"
        }

        try {
            downloadStatus =
                DownloadStatus.OK
            Timber.d("GetRawData.doInBackground STATUS OK")
            return URL(params[0]).readText()
        } catch (e: Exception) {
            val errorMessage = when(e) {
                is MalformedURLException -> {
                    downloadStatus =
                        DownloadStatus.NOT_INITIALIZED
                    "doInBackground: Invalid URL ${e.message}"
                }
                is IOException -> {
                    downloadStatus =
                        DownloadStatus.FAILED_OR_EMPTY
                    "doInBackground: IOException Reading data ${e.message}"
                }
                is SecurityException -> {
                    downloadStatus =
                        DownloadStatus.PERMISSIONS_ERROR
                    "doInBackground: Security Exception: Needs permission? ${e.message}"
                }
                else -> {
                    downloadStatus =
                        DownloadStatus.ERROR
                    "Unknown error: ${e.message}"
                }
            }

            Timber.e(errorMessage)
            return errorMessage;
        }
    }
}
