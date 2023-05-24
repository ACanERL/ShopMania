package com.example.shopmania.ui.Home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopmania.data.repository.ProductsRepository
import com.example.shopmania.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: ProductsRepository):ViewModel() {
    val getCategoryList: MutableLiveData<Resource<List<String>>> = MutableLiveData()

   init {
       getCategories()
   }

    private fun getCategories() = viewModelScope.launch {
        getCategoryList.postValue(Resource.Loading())
        getCategoryList.postValue(handleProductResponse(repository.getAllCategory()))
    }

    private fun handleProductResponse(response: Resource<List<String>>) =
        when (response) {
            is Resource.Success -> Resource.Success(response.data.orEmpty())
            is Resource.Error -> Resource.Error(response.message.orEmpty())
            is Resource.Loading -> Resource.Loading()
        }
}