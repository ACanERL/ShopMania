package com.example.shopmania.ui.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopmania.data.model.CartModel
import com.example.shopmania.data.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private  val repository: ProductsRepository):ViewModel() {
    val getProduct: MutableLiveData<List<CartModel>> = MutableLiveData()
    val getAmount: MutableLiveData<Double> = MutableLiveData(0.0)

    init {
        getProductItem()
    }

  fun getProductItem() = viewModelScope.launch(Dispatchers.IO) {
        val products = repository.getProductToCart()

        // when a new product is added
        products.forEach {
            it.count = 1
        }
        getProduct.postValue(products)

        val totalAmount = products.sumOf {
            (it.price ?: 0.0) * it.count
        }
        getAmount.postValue(totalAmount)
    }

    fun deleteProductFromCart(id:Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteProductFromCart(id)
        getProductItem()
    }

    fun increasePrice(price:Double){
        val currentAmount = getAmount.value ?: 0.0
        val newAmount = currentAmount + price
        getAmount.postValue(newAmount)
    }

    fun decreasePrice(price:Double){
        val currentAmount = getAmount.value ?: 0.0
        val newAmount = currentAmount - price
        getAmount.postValue(newAmount)
    }
}