package com.maxscrub.bw.androidkotlinflickr.activity

import android.os.Bundle
import android.util.Log
import com.maxscrub.bw.androidkotlinflickr.R
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : BaseActivity() {

    private val TAG = "SearchActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        activateToolbar(true)
        Log.d("%s.onCreate", TAG)
    }

}
