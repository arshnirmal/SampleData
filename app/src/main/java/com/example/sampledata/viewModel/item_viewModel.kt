package com.example.sampledata.viewModel

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampledata.model.Item
import com.example.sampledata.network.ApiService
import com.example.sampledata.view.Items
import kotlinx.coroutines.launch


@Composable
fun ItemList(itemList: List<Item>) {
    LazyColumn{
        items(itemList.size) { index ->
            val item = itemList[index]
            Items(item = item)

            if (index < itemList.size - 1) {
                Divider(
                    modifier = Modifier
                        .padding(start = 85.dp, end = 16.dp),
                    color = Color.LightGray,
                    thickness = 1.dp
                )
            }
        }
    }
}
class ItemViewModel : ViewModel() {
    var itemListResponse : List<Item> by mutableStateOf(listOf())
    private var errorMessage : String by mutableStateOf("")

    fun getItemsList() {
        viewModelScope.launch {
            val apiService = ApiService.getInstance()
            try {
                val response = apiService.getItemsList()
                itemListResponse = response.data.items
                println(itemListResponse)
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}