package com.maxscrub.bw.androidkotlinflickr

import android.os.AsyncTask
import org.json.JSONException
import org.json.JSONObject
import timber.log.Timber


class GetFlickrJsonData(private val listener: OnDataAvailable): AsyncTask<String, Void, ArrayList<Photo>>() {

    override fun doInBackground(vararg params: String?): ArrayList<Photo> {
        Timber.d("GetFlickrJsonData.doInBackground")

        val photoList = ArrayList<Photo>()

        try {
            val jsonData = JSONObject(params[0])
            val itemsArray = jsonData.getJSONArray("items");

            for (i in 0 until itemsArray.length()) {
                val jsonPhoto = itemsArray.getJSONObject(i)
                val jsonMedia = jsonPhoto.getJSONObject("media")

                val title = jsonPhoto.getString("title")
                val author = jsonPhoto.getString("author")
                val authorID = jsonPhoto.getString("author_id")
                val tags = jsonPhoto.getString("tags")

                val photoUrl = jsonMedia.getString("m")

                // get bigger image
                val link = photoUrl.replaceFirst("_m.jpg", "_b.jpg")

                val photoObject = Photo(title, author, authorID, link, tags, photoUrl)
                photoList.add(photoObject)

                Timber.d("GetFlickrJsonData.doInBackground $photoObject")
            }
        } catch (e: JSONException) {
            e.printStackTrace()
            Timber.e("GetFlickrJsonData.doInBackground: Error processing JSON data - ${e.message}")
            cancel(true)
            listener.onError(e)
        }

        return photoList
    }

    override fun onPostExecute(result: ArrayList<Photo>) {
        Timber.d("GetFlickrJsonData.onPostExecute")
        super.onPostExecute(result)

        listener.onDataAvailable(result)
    }
}