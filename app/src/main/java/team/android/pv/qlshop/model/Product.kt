package team.android.pv.qlshop.model

import android.os.Parcel
import android.os.Parcelable

class Product() : Parcelable {
    var id:Int=0
    var id_category:Int=0
    var name:String=""
    var description:String=""
    var barcode:String=""
    var price_outs:Long=0
    var price_out:Long=0
    var price_in:Long=0
    var amount:Int=0
    var id_shop:Int=0
    var category=""
    var brand=""
    var note=""
    var unit=""
     private var isSelected:Boolean=false


    fun isSelected():Boolean{
          return isSelected
    }

    fun setSelected(selected: Boolean) {
        isSelected = selected
    }

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        id_category = parcel.readInt()
        name = parcel.readString()
        description = parcel.readString()
        barcode = parcel.readString()
        price_outs = parcel.readLong()
        price_out = parcel.readLong()
        price_in = parcel.readLong()
        amount = parcel.readInt()
        id_shop = parcel.readInt()
        category = parcel.readString()
        brand = parcel.readString()
        note = parcel.readString()
        unit = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(id_category)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(barcode)
        parcel.writeLong(price_outs)
        parcel.writeLong(price_out)
        parcel.writeLong(price_in)
        parcel.writeInt(amount)
        parcel.writeInt(id_shop)
        parcel.writeString(category)
        parcel.writeString(brand)
        parcel.writeString(note)
        parcel.writeString(unit)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }

}