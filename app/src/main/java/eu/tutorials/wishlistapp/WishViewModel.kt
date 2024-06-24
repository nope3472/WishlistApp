package eu.tutorials.wishlistapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.tutorials.wishlistapp.data.Wish
import eu.tutorials.wishlistapp.data.Wishrepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WishViewModel(
    private val wishRepository:Wishrepository=Graph.wishrepository
):ViewModel() {

    var wishliststate by mutableStateOf("")
    var wishlistdescription by mutableStateOf("")

    fun onwishllisttitlechange(newstring: String){
        wishliststate=newstring
    }
    fun onwishllistdescriptionchange(newstring :String){
        wishlistdescription=newstring
    }

    lateinit var getALLWishes:Flow<List<Wish>>

    init {
        viewModelScope.launch {
            getALLWishes=wishRepository.getwishes()
        }
    }

    fun addwish(wish: Wish)
    {
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.addAWish(wish)
        }
    }
    fun updatewish(wish: Wish)
    {
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.updatewish(wish)
        }
    }

    fun getawishbyid(id:Long):Flow<Wish>{
        return wishRepository.getawishbyid(id)
    }
    fun deletewish(wish: Wish)
    {
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.deletewish(wish)
        }
    }
}