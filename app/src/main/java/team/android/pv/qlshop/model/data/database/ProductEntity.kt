package team.android.pv.qlshop.model.data.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class ProductEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid")
    var uid:Int=0

    @ColumnInfo(name = "name")
    var name:String=""

    @ColumnInfo(name = "price_out")
    var price_out:Long=0

    @ColumnInfo(name = "amount")
    var amount:Int=0

    @ColumnInfo(name = "amounts")
    var amounts:Int=0

    @ColumnInfo(name = "sale")
    var sale:Double=0.0

    @ColumnInfo(name = "sale_money")
    var sale_money:Long=0L

    @ColumnInfo(name = "size")
    var size=""



}