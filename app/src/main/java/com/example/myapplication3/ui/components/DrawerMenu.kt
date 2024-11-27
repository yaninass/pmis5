package com.example.myapplication3.ui.components
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Modifier
import androidx.compose.material3.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.myapplication3.R
import com.example.myapplication3.ui.theme.Pink40
import com.example.myapplication3.ui.theme.PurpleGrey40
import com.example.myapplication3.utils.DrawerEvents


@Composable
fun DrawerMenu(onEvent: (DrawerEvents) -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Main Bg Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.fillMaxSize()) {
            Header()
            Body() {event->onEvent(event)}
        }
    }
}

@Composable
fun Header() {
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(170.dp)
        .padding(5.dp),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, PurpleGrey40)
    ) {
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Image(painter = painterResource(id = R.drawable.heading),
                contentDescription = "Header Image",
                modifier=Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Text(text="Cобаки",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(PurpleGrey40)
                    .padding(10.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color= Color.White
            )
        }
    }
}

@Composable
fun Body(onEvent: (DrawerEvents) -> Unit) {
    val list = stringArrayResource(id = R.array.book_categories)
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(list) { index, title ->
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(3.dp),
                colors = CardDefaults.cardColors(containerColor = PurpleGrey40)
            ) {
                Text(
                    text = title,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            onEvent(DrawerEvents.OnItemClick(title, index))
                        }
                        .padding(10.dp)
                        .wrapContentWidth(),
                    fontWeight = FontWeight.Bold,
                    fontSize=16.sp
                )
            }
        }
    }
}




