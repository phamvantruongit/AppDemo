package team.android.pv.qlshop.model.data.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class UserEntity {
    @PrimaryKey
    var email:String=""

    @ColumnInfo(name ="name_shop")
    var name_shop:String=""

    @ColumnInfo(name ="name")
    var name:String=""

    @ColumnInfo(name ="phone")
    var phone:String=""

    @ColumnInfo(name ="id_shop")
    var id_shop=0

    @ColumnInfo(name ="id")
    var id=0

    @ColumnInfo(name ="check_admin")
    var check_admin=""
}