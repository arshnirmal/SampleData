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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsStartWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.progressSemantics
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarColors
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sampledata.model.Item
import com.example.sampledata.ui.theme.SampleDataTheme
import com.example.sampledata.viewModel.ItemGrid
import com.example.sampledata.viewModel.ItemList
import com.example.sampledata.viewModel.ItemViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : ComponentActivity() {
    private val itemViewModel by viewModels<ItemViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleDataTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   if (itemViewModel.itemListResponse.isEmpty()) {
                       itemViewModel.getItemsList()
                       Box(
                           modifier = Modifier.fillMaxSize(),
                           contentAlignment = Alignment.Center
                       ) {
                           CircularProgressIndicator(
                               modifier = Modifier.size(50.dp, 50.dp)
                           )
                       }
                   } else {
                       AppScreen(itemList = itemViewModel.itemListResponse)
                   }
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
            Column {
                TopAppBar(
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = Color(
                            0xFFe7eaf7
                        )
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    title = {
                        Row(
                            modifier = Modifier
                                .padding(start = 16.dp, end = 24.dp, top = 8.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Explore",
                                style = TextStyle(
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                )
                            )
                            Text(
                                text = "Filter",
                                style = TextStyle(
                                    color = Color.Green,
                                    fontSize = 14.sp,
                                )
                            )
                        }
                    }
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .background(Color(0xFFe7eaf7))
                ) {
                    SearchBar(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp)
                            .height(40.dp),
                        query = searchText,
                        onQueryChange = { searchText = it },
                        onSearch = { isSearchActive = true },
                        active = isSearchActive,
                        onActiveChange = { isSearchActive = it },
                        shape = RoundedCornerShape(20.dp),
                        colors = SearchBarDefaults.colors(containerColor = Color(0xFFf7f6f7)),
                        //                                colors = TextFieldDefaults.textFieldColors(containerColor = Color.Red),
                    ) {
                        Text(
                            text = "Search",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray
                        )
                    }
                }
            }
        },
        bottomBar = {
            Column {
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp),
                    color = Color.Gray
                )
                BottomNavigation(
                    elevation = 2.dp,
                    backgroundColor = Color(0xFFfbfafb),
                ) {
                    screens.forEach { screen ->
                        BottomNavigationItem(
                            icon = {
                                   Box(
                                       modifier = Modifier
                                           .size(24.dp)
                                           .background(if (selectedScreen == screen) Color(0xFF5cb075) else Color(0xFFe8e9e9), shape = CircleShape),
                                   )
                            },
                            selected = selectedScreen == screen,
                            onClick = {
                                if(screen == "ListView" || screen == "GridView") {
                                    selectedScreen = screen
                                }
                            }
                        )
                    }
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
                "GridView" -> ItemGrid(itemList = itemList)
            }
        }
    }
}