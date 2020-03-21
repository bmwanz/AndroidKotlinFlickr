package com.maxscrub.bw.androidkotlinflickr.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import timber.log.Timber
import java.io.IOException
import java.io.ObjectStreamException
import java.io.Serializable

@Parcelize
class Photo(
    var title: String,
    var author: String,
    var authorID: String,
    var link: String,
    var tags: String,
    var image: String
) : Parcelable {

    companion object {
        private const val serialVersionUID = 1L
    }

    override fun toString(): String {
        return "Photo(title='$title', author='$author', authorID='$authorID', link='$link', tags='$tags', image='$image')"
    }

    @Throws(IOException::class)
    private fun writeObject(out: java.io.ObjectOutputStream) {
        Timber.d("Photo.writeObject")
        out.writeUTF(title)
        out.writeUTF(author)
        out.writeUTF(authorID)
        out.writeUTF(link)
        out.writeUTF(tags)
        out.writeUTF(image)
    }

    @Throws(IOException::class, ClassNotFoundException::class)
    private fun readObject(inStream: java.io.ObjectInputStream) {
        Timber.d("Photo.readObject")
        title = inStream.readUTF()
        author = inStream.readUTF()
        authorID = inStream.readUTF()
        link = inStream.readUTF()
        tags = inStream.readUTF()
        image = inStream.readUTF()
    }

    @Throws(ObjectStreamException::class)
    private fun readObjectNoData() {
        Timber.d("Photo.readObjectNoData")
    }
}