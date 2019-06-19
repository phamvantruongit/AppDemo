package team.android.pv.qlshop.model.data.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface UserDao {
    @Insert()
    fun addUser(userEntity: UserEntity)

    @Query("SELECT * FROM UserEntity")
    fun getUser() : UserEntity


    @Query("UPDATE UserEntity set login = :login")
    fun updateLogin(login : Boolean)


}