package team.android.pv.qlshop.model.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database( entities = arrayOf(UserEntity::class,ProductEntity::class),version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun productDao() : ProductDao
}