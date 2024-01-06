package com.example.sampledata

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsStartWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sampledata.model.Item
import com.example.sampledata.ui.theme.SampleDataTheme
import com.example.sampledata.view.Items
import com.example.sampledata.viewModel.ItemViewModel

class MainActivity : ComponentActivity() {
    private val itemViewModel by viewModels<ItemViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleDataTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ItemList(itemList = itemViewModel.itemListResponse)
                    itemViewModel.getItemsList()
                }
            }
        }
    }
}

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