package com.example.sampledata

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsStartWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sampledata.model.Item
import com.example.sampledata.ui.theme.SampleDataTheme
import com.example.sampledata.view.Items
import com.example.sampledata.viewModel.ItemViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

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
                    AppScreen(itemList = itemViewModel.itemListResponse)
                    itemViewModel.getItemsList()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen(itemList: List<Item>) {
    val screens = listOf("ListView", "GridView", "Dummy1", "Dummy2", "Dummy3")
    var selectedScreen by remember { mutableStateOf(screens.first()) }

    var searchText by remember { mutableStateOf("") }
    var isSearchActive by remember { mutableStateOf(false) }


    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color.LightGray),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp), // Adjust the height as needed
                title = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp, bottom = 4.dp), // Adjust the top and bottom padding as needed
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(start = 16.dp, end = 24.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Explore",
                                style = MaterialTheme.typography.titleLarge,
                            )
                            Text(
                                text = "Filter",
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.Cyan
                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp)
                                .padding(end = 16.dp)
                        ) {
                            SearchBar(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                query = searchText,
                                onQueryChange = { searchText = it },
                                onSearch = { isSearchActive = true },
                                active = isSearchActive,
                                onActiveChange = { isSearchActive = it },
                            ) {

                            }
                        }
                    }
                },
            )
        },
        bottomBar = {
            BottomNavigation{
                screens.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(Icons.Rounded.AddCircle, contentDescription = null) },
                        selected = selectedScreen == screen,
                        onClick = { selectedScreen = screen }
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            when (selectedScreen) {
                "ListView" -> ItemList(itemList = itemList)
                "GridView" -> ItemList(itemList = itemList)
                "Dummy1" -> Box(){}
                "Dummy2" -> Box(){}
                "Dummy3" -> Box(){}
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