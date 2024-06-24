package eu.tutorials.wishlistapp.data

import kotlinx.coroutines.flow.Flow

class Wishrepository (private val wishDAO: WishDAO){

   suspend fun addAWish(wish: Wish){
        wishDAO.addAWish(wish)
    }
    fun getwishes(): Flow<List<Wish>>  = wishDAO.getAllWishes()
     fun getawishbyid(id:Long):Flow<Wish>{
        return wishDAO.getwishbyid(id)
    }

    suspend fun updatewish(wish: Wish){
        wishDAO.updateAWish(wish)
    }

    suspend fun deletewish(wish: Wish){
        wishDAO.deletewish(wish)
    }
}