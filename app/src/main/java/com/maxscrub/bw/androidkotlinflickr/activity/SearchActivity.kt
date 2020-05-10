package com.maxscrub.bw.androidkotlinflickr.activity

import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import com.maxscrub.bw.androidkotlinflickr.R
import timber.log.Timber

class SearchActivity : BaseActivity() {

    private val TAG = "SearchActivity"
    private var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        activateToolbar(true)
        Timber.d("%s.onCreate", TAG)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        return true
    }
}
