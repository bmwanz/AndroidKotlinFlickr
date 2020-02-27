package com.maxscrub.bw.androidkotlinflickr.activity

import android.annotation.SuppressLint
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.maxscrub.bw.androidkotlinflickr.R
import timber.log.Timber

internal const val FLICKR_QUERY = "FLICKR_QUERY"
internal const val PHOTO_TRANSFER = "PHOTO_TRANSFER"

@SuppressLint("Registered")
open class BaseActivity: AppCompatActivity() {

    private val TAG = "BaseActivity"

    internal fun activateToolbar(enableHome: Boolean) {
        Timber.d("%s.activateToolbar", TAG)

        var toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(enableHome)

    }
}