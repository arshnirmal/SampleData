package com.example.sampledata.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampledata.model.Item
import com.example.sampledata.network.ApiService
import kotlinx.coroutines.launch

class ItemViewModel : ViewModel() {
    var itemListResponse : List<Item> by mutableStateOf(listOf())
//    var isLoading : Boolean by mutableStateOf(false)
//    var isError : Boolean by mutableStateOf(false)
private var errorMessage : String by mutableStateOf("")

    fun getItemsList() {
        viewModelScope.launch {
//            isLoading = true
            val apiService = ApiService.getInstance()
            try {
                val response = apiService.getItemsList()
                itemListResponse = response.data.items
                println(itemListResponse)
            } catch (e: Exception) {
//                isError = true
//                isLoading = false
                errorMessage = e.message.toString()
            }
//            isLoading = false
        }
    }
}