package com.maxscrub.bw.androidkotlinflickr.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.maxscrub.bw.androidkotlinflickr.R
import com.maxscrub.bw.androidkotlinflickr.model.Photo
import com.squareup.picasso.Picasso
import timber.log.Timber


class FlickrRecyclerViewAdapter(private var photoList: List<Photo>) :
    RecyclerView.Adapter<FlickrImageViewHolder>() {

    private val TAG = "FlickrRVadapt"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlickrImageViewHolder {
        // called by layout manager when need new view
        Timber.d("%s.onCreateViewHolder", TAG)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.browse, parent, false)
        return FlickrImageViewHolder(view)
    }

    override fun getItemCount(): Int {
//        Timber.d("%s.getItemCount", TAG)
        return if (photoList.isNotEmpty()) photoList.size else 0
    }

    override fun onBindViewHolder(holder: FlickrImageViewHolder, position: Int) {
        // called by layout manager when need new data in existing view

        if (photoList.isEmpty()) {
            holder.thumbnail.setImageResource(R.drawable.placeholder)
            holder.title.setText(R.string.empty_photo)
        } else {
            val photoItem = photoList[position]
//        Timber.d("%s.onBindViewHolder: ${photoItem.title} --> ${position}", TAG)
            Picasso.with(holder.thumbnail.context).load(photoItem.image)
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(holder.thumbnail)
            holder.title.text = photoItem.title
        }
    }

    fun loadNewData(newPhotos: List<Photo>) {
        Timber.d("%s.loadNewData", TAG)
        photoList = newPhotos
        notifyDataSetChanged()
    }

    fun getPhoto(position: Int): Photo? {
        Timber.d("%s.getPhoto", TAG)
        return if (photoList.isNotEmpty()) photoList[position] else null
    }
}

class FlickrImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var thumbnail: ImageView = view.findViewById(R.id.thumbnail)
    var title: TextView = view.findViewById(R.id.title)

}