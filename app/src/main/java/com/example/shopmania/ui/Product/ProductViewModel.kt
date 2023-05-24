package com.example.shopmania.ui.Product

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopmania.data.model.ProductResponseModel
import com.example.shopmania.data.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val repository: ProductsRepository):ViewModel() {

    val getCategoryProduct:MutableLiveData<com.example.shopmania.util.Resource<List<ProductResponseModel>>> = MutableLiveData()

    fun getCategoryProduct(categoryName:String)=viewModelScope.launch {
        getCategoryProduct.postValue(handleProductResponse(repository.getSpecificCategory(categoryName)))
    }
    private fun handleProductResponse(response: com.example.shopmania.util.Resource<List<ProductResponseModel>>) =
        when (response) {
            is com.example.shopmania.util.Resource.Success -> com.example.shopmania.util.Resource.Success(response.data.orEmpty())
            is com.example.shopmania.util.Resource.Error -> com.example.shopmania.util.Resource.Error(response.message.orEmpty())
            is com.example.shopmania.util.Resource.Loading -> com.example.shopmania.util.Resource.Loading()
        }
}