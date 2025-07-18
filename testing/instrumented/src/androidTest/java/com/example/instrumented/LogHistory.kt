package com.example.instrumented

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.TypeParceler


//@Parcelize
class LogHistory(
    private val myEntries: MutableList<Pair<String, Long>>,
) : Parcelable {

    constructor(parcel: Parcel) : this(
        myEntries = mutableListOf<Pair<String, Long>>().apply {
            repeat(parcel.readInt()) {
                add(parcel.readString()!! to parcel.readLong())
            }
        }
    )

    fun addEntry(entry: Pair<String, Long>) {
        myEntries?.add(entry)
    }

    fun getData(): List<Pair<String, Long>> = myEntries.orEmpty()
    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(myEntries.size)
        myEntries.forEach {
            dest.writeString(it.first)
            dest.writeLong(it.second)
        }
    }

    companion object CREATOR : Parcelable.Creator<LogHistory> {
        override fun createFromParcel(source: Parcel): LogHistory? {
            return LogHistory(source)
        }

        override fun newArray(size: Int): Array<out LogHistory?>? {
            return arrayOfNulls(size)
        }

    }

}

@TypeParceler<LogHistory, LogHistory2Parceler>()
@Parcelize
class LogHistory2(
    val name: String
) : Parcelable

object LogHistory2Parceler : kotlinx.android.parcel.Parceler<LogHistory2> {
    override fun LogHistory2.write(
        parcel: Parcel,
        flags: Int
    ) {
        parcel.writeString(name)
    }

    override fun create(parcel: Parcel): LogHistory2 {
        return LogHistory2(parcel.readString()!!)
    }

}