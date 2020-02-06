package com.maxscrub.bw.androidkotlinflickr

import android.net.Uri
import android.nfc.NdefRecord.createUri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import java.lang.Exception

class MainActivity : AppCompatActivity(), OnDownloadComplete, OnDataAvailable {

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.plant(Timber.DebugTree())
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        Timber.d("MainActivity.onCreate")

        val url = createUri("https://api.flickr.com/services/feeds/photos_public.gne", "android,oreo", "en-us", true)
        val getRawData = GetRawData(this)
        getRawData.execute(url)

    }

    private fun createUri(baseUrl: String, searchCriteria: String, language: String, matchAll: Boolean) : String {

        val uri =  Uri
            .parse(baseUrl)
            .buildUpon()
            .appendQueryParameter("tags", searchCriteria)
            .appendQueryParameter("tagmode", if (matchAll) "ALL" else "ANY")
            .appendQueryParameter("lang", language)
            .appendQueryParameter("format", "json")
            .appendQueryParameter("nojsoncallback", "1")
            .build().toString()

        Timber.d("MainActivity.createUri %s", uri)

        return uri
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Timber.d("MainActivity.onCreateOptionsMenu")
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        Timber.d("MainActivity.onOptionItemSelected")
        return super.onOptionsItemSelected(item)
    }

    override fun onDownloadComplete(data: String, status: DownloadStatus) {
        if (status == DownloadStatus.OK) {
//            Timber.d("MainActivity onDownloadComplete OK\ndata = $data")
            Timber.d("MainActivity.onDownloadComplete OK")
            val getFlickrJsonData = GetFlickrJsonData(this)
            getFlickrJsonData.execute(data)
        } else {
            Timber.d("MainActivity.onDownloadComplete\nStatus = $status\nError = $data")
        }
    }

    override fun onDataAvailable(data: List<Photo>) {
        Timber.d("MainActivity.onDataAvailable: Data - $data")
    }

    override fun onError(exception: Exception) {
        Timber.e("MainActivity.onError: Exception - $exception")
    }
}
