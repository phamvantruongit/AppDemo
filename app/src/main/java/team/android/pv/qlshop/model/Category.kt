package team.android.pv.qlshop.model

import android.os.Parcel
import android.os.Parcelable

class Category() : Parcelable {
     var id:Int=0
     var id_shop:Int=0
     var name:String=""

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        id_shop = parcel.readInt()
        name = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(id_shop)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Category> {
        override fun createFromParcel(parcel: Parcel): Category {
            return Category(parcel)
        }

        override fun newArray(size: Int): Array<Category?> {
            return arrayOfNulls(size)
        }
    }

}