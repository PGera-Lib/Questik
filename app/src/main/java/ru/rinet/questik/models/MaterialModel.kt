package ru.rinet.questik.models

import android.os.Parcel
import android.os.Parcelable

data class MaterialModel(
    var id: String? = "",
    var plu: String? = "",
    var name: String? = "",
    var price: String? = "",
    var metrics_id: String? = "",
    var category_id: String? = "",
) : Parcelable {
    constructor(parcel: Parcel) : this(
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
        parcel.writeString(plu)
        parcel.writeString(name)
        parcel.writeString(price)
        parcel.writeString(metrics_id)
        parcel.writeString(category_id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MaterialModel> {
        override fun createFromParcel(parcel: Parcel): MaterialModel {
            return MaterialModel(parcel)
        }

        override fun newArray(size: Int): Array<MaterialModel?> {
            return arrayOfNulls(size)
        }
    }
}
