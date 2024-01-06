package com.example.sampledata.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.sampledata.model.Item
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.sampledata.R

@Composable
fun SingleListItem(item: Item){
    Box(
        modifier = Modifier
            .height(80.dp)
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .weight(1f)
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = item.image,
                        builder = {
                            crossfade(true)
                            placeholder(R.drawable.ic_launcher_background)
                            transformations(CircleCropTransformation())
                        }
                    ),
                    contentDescription = "Item Image",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape),
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(4f)
                    .padding(start = 10.dp, top = 4.dp)
            ) {
                BasicText(
                    text = item.name,
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                    ),
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                ) {
                    BasicText(
                        text = "MRP: ",
                        style = TextStyle(
                            color = Color.Gray,
                        ),
                    )
                    BasicText(
                        text = item.price,
                        style = TextStyle(
                            color = Color.Black,
                        ),
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    BasicText(
                        text = item.extra ?: "",
                        style = TextStyle(
                            fontWeight = FontWeight.Light,
                        ),
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun SingleGridItem(item: Item){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current).data(data = item.image).apply(block = fun ImageRequest.Builder.() {
                        crossfade(true)
                        placeholder(R.drawable.ic_launcher_background)
                        transformations(CircleCropTransformation())
                    }).build()
                ),
                contentDescription = "Item Image",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape),
            )
        }
        BasicText(
            text = item.name,
            modifier = Modifier.padding(top = 4.dp),
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
            ),
        )
        BasicText(
            text = item.price,
            modifier = Modifier.padding(bottom = 16.dp),
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
            ),
        )
    }
}