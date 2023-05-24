package com.example.shopmania.ui.Favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopmania.data.model.FavoriteModel
import com.example.shopmania.data.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: ProductsRepository):ViewModel() {

    val getFavoriteProduct:MutableLiveData<List<FavoriteModel>> = MutableLiveData()

    init {
        getFavorite()
    }

    fun getFavorite()=viewModelScope.launch(Dispatchers.IO){
            getFavoriteProduct.postValue(repository.getProductToFavorite())
    }
     fun deleteFavoriteItem(favId:Int)=viewModelScope.launch(Dispatchers.IO){
                repository.deleteProductFromFavorite(favId)
        getFavorite()
    }
}