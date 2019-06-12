package team.android.pv.qlshop.model

import android.os.Parcel
import android.os.Parcelable


class User() : Parcelable  {
    var email:String=""
    var name_shop:String=""
    var name:String=""
    var phone:String=""
    var password:String=""
    var id_shop=0
    var id=0
    var check_admin=""

   constructor(parcel: Parcel) : this() {
      email = parcel.readString()
      name_shop = parcel.readString()
      name = parcel.readString()
      phone = parcel.readString()
      password = parcel.readString()
      id_shop = parcel.readInt()
      id = parcel.readInt()
      check_admin = parcel.readString()
   }

   override fun writeToParcel(parcel: Parcel, flags: Int) {
      parcel.writeString(email)
      parcel.writeString(name_shop)
      parcel.writeString(name)
      parcel.writeString(phone)
      parcel.writeString(password)
      parcel.writeInt(id_shop)
      parcel.writeInt(id)
      parcel.writeString(check_admin)
   }

   override fun describeContents(): Int {
      return 0
   }

   companion object CREATOR : Parcelable.Creator<User> {
      override fun createFromParcel(parcel: Parcel): User {
         return User(parcel)
      }

      override fun newArray(size: Int): Array<User?> {
         return arrayOfNulls(size)
      }
   }
}