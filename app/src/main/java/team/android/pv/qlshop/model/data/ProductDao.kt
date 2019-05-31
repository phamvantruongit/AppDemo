package team.android.pv.qlshop.model.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert


@Dao
interface ProductDao {


    @Insert
    fun inSertProduct(productEntity: ProductEntity)

    @android.arch.persistence.room.Query("SELECT * FROM ProductEntity")
    fun getAllProduct(): List<ProductEntity>


    @android.arch.persistence.room.Query("UPDATE ProductEntity SET price_out =(:price_out) WHERE id in (:id)")
    fun updateProduct(id: Int, price_out: String)


}