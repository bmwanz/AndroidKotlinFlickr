package com.maxscrub.bw.androidkotlinflickr.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.maxscrub.bw.androidkotlinflickr.R
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setSupportActionBar(toolbar)

//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}
