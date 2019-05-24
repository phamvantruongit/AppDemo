package team.android.pv.qlshop.model.data

import io.realm.RealmObject

open class User: RealmObject() {
    var email:String=""
    var name_shop:String=""
    var name:String=""
    var phone:String=""
    var id_shop=0
    var id=0
    var check_admin=""
}