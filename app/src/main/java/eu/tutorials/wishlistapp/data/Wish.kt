package eu.tutorials.wishlistapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import eu.tutorials.wishlistapp.AppBarView


@Entity(tableName = "wish_table")
data class Wish(
    @PrimaryKey(autoGenerate = true)
    val id: Long=0L,
    @ColumnInfo("wish_title")
    val title :String="",
    @ColumnInfo("wish_disc")
    val description:String=""
)

object DummyWish{
    val WishList = listOf(
        Wish(title = "Samsung S21 Fe",
            description = "A good Phone"),
        Wish(title = "Zero to One",
            description = "A very Good Book")


    )


}
