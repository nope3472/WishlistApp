package eu.tutorials.wishlistapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow



@Dao
abstract class WishDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addAWish(wishEntity: Wish)

    @Query("Select * from `wish_table`")
    abstract fun getAllWishes(): Flow<List<Wish>>

    @Update
    abstract suspend fun updateAWish(wishEntity: Wish)

    @Delete
    abstract  suspend  fun deletewish(wishEntity: Wish)

    @Query("Select * from `wish_table` where  id =:id")
    abstract fun getwishbyid(id:Long):Flow<Wish>
}
