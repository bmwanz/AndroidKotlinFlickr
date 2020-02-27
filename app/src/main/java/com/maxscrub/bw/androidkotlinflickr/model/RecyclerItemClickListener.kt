package com.maxscrub.bw.androidkotlinflickr.model

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.core.view.GestureDetectorCompat
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

    private val gestureDetector = GestureDetectorCompat(context, object: GestureDetector.SimpleOnGestureListener() {

        override fun onSingleTapUp(e: MotionEvent): Boolean {
            Timber.d("%s.onSingleTapUp", TAG)
            val childView = recyclerView.findChildViewUnder(e.x, e.y)
            listener.onItemClick(childView!!, recyclerView.getChildAdapterPosition(childView))
            return true
        }

        override fun onLongPress(e: MotionEvent) {
            Timber.d("%s.onLongPress", TAG)
            val childView = recyclerView.findChildViewUnder(e.x, e.y)
            listener.onItemLongClick(childView!!, recyclerView.getChildAdapterPosition(childView))
        }

    })

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        Timber.d("%s.onInterceptTouchEvent: $e", TAG)
        val result = gestureDetector.onTouchEvent(e)
        Timber.d("%s.onInterceptTouchEvent return: $result", TAG)
        return result
    }
}