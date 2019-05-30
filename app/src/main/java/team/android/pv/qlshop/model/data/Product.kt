package team.android.pv.qlshop.model.data

import io.realm.RealmModel
import io.realm.annotations.PrimaryKey

class Product:RealmModel {
    @PrimaryKey
    var id:Int=0

    var id_category:Int=0
    var name:String=""
    var price_outs:Long=0
    var price_out:Long=0
    var amount:Int=0
    var id_shop:Int=0
    var amount_buy=0
}