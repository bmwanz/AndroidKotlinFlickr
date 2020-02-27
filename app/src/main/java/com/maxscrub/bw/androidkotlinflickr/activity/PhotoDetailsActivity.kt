package com.maxscrub.bw.androidkotlinflickr.activity

import android.os.Bundle
import com.maxscrub.bw.androidkotlinflickr.R

class PhotoDetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_details)

        activateToolbar(true)


    }

}
