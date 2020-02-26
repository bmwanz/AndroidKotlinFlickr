package com.maxscrub.bw.androidkotlinflickr.activity

import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.maxscrub.bw.androidkotlinflickr.*
import com.maxscrub.bw.androidkotlinflickr.adapter.FlickrRecyclerViewAdapter
import com.maxscrub.bw.androidkotlinflickr.interfaces.OnDataAvailable
import com.maxscrub.bw.androidkotlinflickr.interfaces.OnDownloadComplete
import com.maxscrub.bw.androidkotlinflickr.model.GetFlickrJsonData
import com.maxscrub.bw.androidkotlinflickr.model.Photo
import com.maxscrub.bw.androidkotlinflickr.task.DownloadStatus
import com.maxscrub.bw.androidkotlinflickr.task.GetRawData

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import timber.log.Timber
import java.lang.Exception

class MainActivity : AppCompatActivity(),
    OnDownloadComplete,
    OnDataAvailable {

    private val flickrRecyclerViewAdapter = FlickrRecyclerViewAdapter(ArrayList())

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.plant(Timber.DebugTree())
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        Timber.d("MainActivity.onCreate")

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = flickrRecyclerViewAdapter

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
            val getFlickrJsonData =
                GetFlickrJsonData(this)
            getFlickrJsonData.execute(data)
        } else {
            Timber.d("MainActivity.onDownloadComplete\nStatus = $status\nError = $data")
        }
    }

    override fun onDataAvailable(data: List<Photo>) {
        Timber.d("MainActivity.onDataAvailable")
        flickrRecyclerViewAdapter.loadNewData(data)
    }

    override fun onError(exception: Exception) {
        Timber.e("MainActivity.onError: Exception - $exception")
    }
}
