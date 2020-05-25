package com.maxscrub.bw.androidkotlinflickr.activity

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import androidx.preference.PreferenceManager
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

    // menu is guaranteed, don't need ?
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        Timber.d("%s.onCreateOptionsMenu", TAG)
        menuInflater.inflate(R.menu.menu_search, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.app_bar_search).actionView as SearchView
        val searchableInfo = searchManager.getSearchableInfo(componentName)
        searchView?.setSearchableInfo(searchableInfo)

        // make it open and ready for input
        searchView?.isIconified = false

        searchView?.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Timber.d("$TAG.onQueryTextSubmit")

                val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
                sharedPreferences.edit().putString(FLICKR_QUERY, query).apply()
                searchView?.clearFocus()

                finish()
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })

        searchView?.setOnCloseListener {
            finish()
            false
        }

        return true
    }
}
