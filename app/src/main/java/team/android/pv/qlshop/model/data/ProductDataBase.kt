package team.android.pv.qlshop.model.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = arrayOf(ProductEntity::class),version = 1)
abstract class ProductDataBase :RoomDatabase() {
    abstract fun productDataBase():ProductDao

    companion object{
        private var INSTANCE:ProductDataBase?=null
        fun getInstance(context: Context):ProductDataBase?{
            if(INSTANCE==null){
                synchronized(ProductDataBase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                       ProductDataBase::class.java, "data.db")
                        .build()
                }
            }
             return INSTANCE
        }
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}