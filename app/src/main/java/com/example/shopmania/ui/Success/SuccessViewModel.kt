package com.example.shopmania.ui.Success

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopmania.data.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SuccessViewModel @Inject constructor(private val repository: ProductsRepository):ViewModel() {

    fun clearCart()=viewModelScope.launch(Dispatchers.IO){
        repository.clearCart()
    }
}