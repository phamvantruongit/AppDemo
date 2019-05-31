package team.android.pv.qlshop.model.data
import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import io.realm.annotations.PrimaryKey
@Entity(tableName = "ProductEntity")
data class ProductEntity(@PrimaryKey var id:Int, @ColumnInfo(name = "name") var name: String,
                         @ColumnInfo(name ="amount") var amount :Int,
                         @ColumnInfo(name = "price_out") var price_out: Long)

