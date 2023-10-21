package com.example.cathaybktest.model

import android.os.Parcel
import android.os.Parcelable

data class ApiResponse(
    val total: Int,
    val data: List<Attraction>
)

data class Attraction(
    val id: Int,
    val name: String,
    val name_zh: String,
    val open_status: Int,
    val introduction: String,
    val open_time: String,
    val zipcode: String,
    val distric: String,
    val address: String,
    val tel: String,
    val fax: String,
    val email: String,
    val months: String,
    val nlat: Double,
    val elong: Double,
    val official_site: String,
    val facebook: String,
    val ticket: String,
    val remind: String,
    val staytime: String,
    val modified: String,
    val url: String,
    val category: List<Category>,
    val images: Array<Image>
)

data class Category(
    val id: Int,
    val name: String
)

data class Image(
    val src: String?,
    val subject: String?,
    val ext: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(src)
        parcel.writeString(subject)
        parcel.writeString(ext)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Image> {
        override fun createFromParcel(parcel: Parcel): Image {
            return Image(parcel)
        }

        override fun newArray(size: Int): Array<Image?> {
            return arrayOfNulls(size)
        }
    }
}


