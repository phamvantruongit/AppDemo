package team.android.pv.qlshop.model.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class Product :RealmObject() {
    @PrimaryKey
    var uid:Int=0
    var name:String=""
    var price_out:Long=0
    var amount:Int=0
    var amounts:Int=0
}