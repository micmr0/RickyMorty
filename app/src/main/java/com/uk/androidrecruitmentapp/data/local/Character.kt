package com.uk.androidrecruitmentapp.data.local

import android.os.Parcel
import android.os.Parcelable
import java.util.*

class Character() : Parcelable {
    var info: InfoCharacter? = null
    var results: Array<ResultCharacter>? = null

    constructor(parcel: Parcel) : this() {
        info = parcel.readParcelable(InfoCharacter::class.java.classLoader)
        results = parcel.createTypedArray(ResultCharacter)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(info, flags)
        parcel.writeTypedArray(results, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Character> {
        override fun createFromParcel(parcel: Parcel): Character {
            return Character(parcel)
        }

        override fun newArray(size: Int): Array<Character?> {
            return arrayOfNulls(size)
        }
    }

}

class InfoCharacter() : Parcelable {
    private var count = 0
    private var pages = 0
    private var next: String? = null
    private var prev: String? = null

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

    companion object CREATOR : Parcelable.Creator<InfoCharacter> {
        override fun createFromParcel(parcel: Parcel): InfoCharacter {
            return InfoCharacter(parcel)
        }

        override fun newArray(size: Int): Array<InfoCharacter?> {
            return arrayOfNulls(size)
        }
    }

}

class ResultCharacter() : Parcelable {
    private var id = 0
    var name: String? = null
    private var status: String? = null
    private var species: String? = null
    private var type: String? = null
    private var gender: String? = null
    private var origin: Origin? = null
    private var location: Origin? = null
    private var image: String? = null
    private var episode: List<String>? = null
    private var url: String? = null
    private var created: Date? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        name = parcel.readString()
        status = parcel.readString()
        species = parcel.readString()
        type = parcel.readString()
        gender = parcel.readString()
        image = parcel.readString()
        episode = parcel.createStringArrayList()
        url = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(status)
        parcel.writeString(species)
        parcel.writeString(type)
        parcel.writeString(gender)
        parcel.writeString(image)
        parcel.writeStringList(episode)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ResultCharacter> {
        override fun createFromParcel(parcel: Parcel): ResultCharacter {
            return ResultCharacter(parcel)
        }

        override fun newArray(size: Int): Array<ResultCharacter?> {
            return arrayOfNulls(size)
        }
    }
}

class Origin {
    private var name: String? = null
    private var url: String? = null

}

