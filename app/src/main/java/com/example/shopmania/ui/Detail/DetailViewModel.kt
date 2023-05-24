package com.example.shopmania.ui.Detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopmania.data.model.CartModel
import com.example.shopmania.data.model.FavoriteModel
import com.example.shopmania.data.model.ProductResponseModel
import com.example.shopmania.data.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: ProductsRepository,private val savedStateHandle: SavedStateHandle):ViewModel(){
    private var _product: MutableLiveData<ProductResponseModel> = MutableLiveData()
    val product get() = _product

    private var _favState:MutableLiveData<Pair<Boolean,Boolean>> = MutableLiveData()
    val favState get() = _favState

    init {
        getProductModel()
    }

    private fun getProductModel(){
        savedStateHandle.get<ProductResponseModel>("product")?.let {
            _product.value = it
            _favState.value = Pair(it.isFavorite,false)
        }
    }

    fun addProductToCart()=viewModelScope.launch {
        _product.value.let {
            if (it != null) {
                repository.addProductToCart(
                    CartModel(id = it.id, image = it.image, title = it.title, price = it.price,it.count)
                )
            }
        }
    }

    fun setFavorite()=viewModelScope.launch {
      _product.value.let {
          if (_favState.value?.first == true){
              if (it != null) {
                  repository.deleteProductFromFavorite(it.id)
              }
          }
          else{
              if (it != null) {
                  repository.addProductToFavorite(
                      FavoriteModel(it.id, it.image, it.title, it.price)
                  )
              }
          }
          _favState.value = Pair(!_favState.value?.first!!,true)
      }
    }
}