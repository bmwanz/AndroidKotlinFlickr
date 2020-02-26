package com.maxscrub.bw.androidkotlinflickr.model

import android.content.Context
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView
import com.maxscrub.bw.androidkotlinflickr.interfaces.OnRecyclerClickListener
import timber.log.Timber


class RecyclerItemClickListener(
    context: Context,
    recyclerView: RecyclerView,
    private val listener: OnRecyclerClickListener
) :
    RecyclerView.SimpleOnItemTouchListener() {

    private val TAG = "RecyclerItemClickListen"

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        Timber.d("%s.onInterCeptTouchEvent: $e", TAG)
        return true
    }
}