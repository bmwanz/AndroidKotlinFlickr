package com.maxscrub.bw.androidkotlinflickr

import android.os.AsyncTask
import org.json.JSONObject
import timber.log.Timber


class GetFlickrJsonData(private val listener: OnDataAvailable): AsyncTask<String, Void, ArrayList<Photo>>() {

    override fun doInBackground(vararg params: String?): ArrayList<Photo> {
        Timber.d("GetFlickrJsonData doInBackground")
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
                val photoUrl = jsonPhoto.getString("m")

                // get bigger image
                val link = photoUrl.replaceFirst("_m.jpg", "_b.jpg")
            }
        } catch (Exception e) {

        }
    }

    override fun onPostExecute(result: ArrayList<Photo>?) {
        Timber.d("GetFlickrJsonData onPostExecute")
        super.onPostExecute(result)
    }
}