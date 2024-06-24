package eu.tutorials.wishlistapp

import android.content.Context
import androidx.room.Room
import eu.tutorials.wishlistapp.data.WishDatabase
import eu.tutorials.wishlistapp.data.Wishrepository

object Graph {
    lateinit var database: WishDatabase

    val wishrepository by lazy {
        Wishrepository(wishDAO = database.wishDao())
    }
    fun provide(context: Context){
        database= Room.databaseBuilder(context,WishDatabase::class.java,"wishlist.db").build()
    }
}