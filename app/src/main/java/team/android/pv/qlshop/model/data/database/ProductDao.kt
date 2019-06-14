package team.android.pv.qlshop.model.data.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query


@Dao
interface ProductDao {

    @Insert()
    fun addProduct(productEntity: ProductEntity)

    @Query("SELECT * FROM ProductEntity")
    fun getListProduct(): LiveData<List<ProductEntity>>


    @Query("SELECT * FROM ProductEntity")
    fun getAllListProduct(): List<ProductEntity>


    @Query("SELECT * FROM ProductEntity WHERE uid in (:id) ")
    fun getProduct(id:Int) : ProductEntity




    @Query(" DELETE FROM ProductEntity WHERE uid in(:id)")
    fun deleteProduct(id: Int)


    @Query("UPDATE ProductEntity SET amount= :editamout WHERE uid in (:id) ")
    fun updateAmount(editamout:Int,id: Int)


    @Query("UPDATE ProductEntity SET sale= :sale WHERE uid in (:id) ")
    fun updateSale(sale:Long,id: Int)


    @Query("UPDATE ProductEntity SET sale= :sale  ")
    fun updateAllSale(sale:Long)


    @Query("UPDATE ProductEntity SET sale_money= :sale WHERE uid in (:id) ")
    fun updateSaleMoney(sale:Long,id: Int)



}