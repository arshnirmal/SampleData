package com.example.sampledata.view

import android.graphics.drawable.Drawable
import androidx.compose.animation.core.Transition
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.sampledata.model.Item
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.example.sampledata.R

@Composable
fun Items(item: Item){
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