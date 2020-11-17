package com.uk.androidrecruitmentapp.data.local

import android.os.Parcel
import android.os.Parcelable

class Location() : Parcelable{
    var locationInfo: LocationInfo? = null
    var results: List<LocationResult>? = emptyList()

    constructor(parcel: Parcel) : this() {
        locationInfo = parcel.readParcelable(LocationInfo::class.java.classLoader)
        results = parcel.createTypedArrayList(LocationResult)!!
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Location> {
        override fun createFromParcel(parcel: Parcel): Location {
            return Location(parcel)
        }

        override fun newArray(size: Int): Array<Location?> {
            return arrayOfNulls(size)
        }
    }
}

class LocationInfo() : Parcelable{
    var count = 0
    var pages = 0
    var next: String? = null
    var prev: String? = null

    constructor(parcel: Parcel) : this() {
        count = parcel.readInt()
        pages = parcel.readInt()
        next = parcel.readString()
        prev = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(count)
        parcel.writeInt(pages)
        parcel.writeString(next)
        parcel.writeString(prev)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LocationInfo> {
        override fun createFromParcel(parcel: Parcel): LocationInfo {
            return LocationInfo(parcel)
        }

        override fun newArray(size: Int): Array<LocationInfo?> {
            return arrayOfNulls(size)
        }
    }

}

class LocationResult() : Parcelable{
    var id = 0
    var name: String? = null
    var type: String? = null
    var dimension: String? = null
    lateinit var residents: List<String>
    var url: String? = null
    var created: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        name = parcel.readString()
        type = parcel.readString()
        dimension = parcel.readString()
        residents = parcel.createStringArrayList()!!
        url = parcel.readString()
        created = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(type)
        parcel.writeString(dimension)
        parcel.writeStringList(residents)
        parcel.writeString(url)
        parcel.writeString(created)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LocationResult> {
        override fun createFromParcel(parcel: Parcel): LocationResult {
            return LocationResult(parcel)
        }

        override fun newArray(size: Int): Array<LocationResult?> {
            return arrayOfNulls(size)
        }
    }
}