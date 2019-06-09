package team.android.pv.qlshop.model

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable

@SuppressLint("ParcelCreator")
class Supplier() : Parcelable {
    var id:Int=0
    var phone:Int=0
    var name:String=""
    var id_shop=0
    var address:String=""
    var email :String=""
    var description :String=""

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        phone = parcel.readInt()
        name = parcel.readString()
        id_shop = parcel.readInt()
        address = parcel.readString()
        email = parcel.readString()
        description = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(phone)
        parcel.writeString(name)
        parcel.writeInt(id_shop)
        parcel.writeString(address)
        parcel.writeString(email)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Supplier> {
        override fun createFromParcel(parcel: Parcel): Supplier {
            return Supplier(parcel)
        }

        override fun newArray(size: Int): Array<Supplier?> {
            return arrayOfNulls(size)
        }
    }
}