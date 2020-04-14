package com.example.sklepallegro.models

import android.os.Parcel
import android.os.Parcelable


data class Price(val amount: Double, val currency: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readDouble(),
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(amount)
        parcel.writeString(currency)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Price> {
        override fun createFromParcel(parcel: Parcel): Price {
            return Price(parcel)
        }

        override fun newArray(size: Int): Array<Price?> {
            return arrayOfNulls(size)
        }
    }
}
