package ru.rinet.questik.models

import android.os.Parcel
import android.os.Parcelable

data class JobsModel(
    val id: String? = "",
    var name: String? = "",
    var price: String? = "",
    var metrics_id: String? = "",
    var category_id: String? = "",
    var price_inzh: String? = "",
    var price_nalog_zp: String? = "",
    var price_zp: String? = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(price)
        parcel.writeString(metrics_id)
        parcel.writeString(category_id)
        parcel.writeString(price_inzh)
        parcel.writeString(price_nalog_zp)
        parcel.writeString(price_zp)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<JobsModel> {
        override fun createFromParcel(parcel: Parcel): JobsModel {
            return JobsModel(parcel)
        }

        override fun newArray(size: Int): Array<JobsModel?> {
            return arrayOfNulls(size)
        }
    }
}
