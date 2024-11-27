package com.example.myapplication3.ui.components

import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication3.MainViewModel
import com.example.myapplication3.ui.theme.MyRed
import com.example.myapplication3.ui.theme.Pink40
import com.example.myapplication3.utils.ListItem


@Composable
fun MainListItem(mainViewModel: MainViewModel = hiltViewModel(), item: ListItem, onClick: (ListItem)->Unit) {
//    Row(
//        modifier = Modifier
//            .padding(8.dp)
//            .fillMaxWidth().clickable {
//                Log.d("MainListItem", "Item clicked: ${item.imageName}, ${item.htmlName}")
//                onClick(item)},
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        AssetImage(imageName =item.imageName,
//            contentDescription = item.title,
//            modifier = Modifier.size(170.dp)
//        )
//        Text(
//            text = item.title,
//            style = MaterialTheme.typography.headlineMedium
//        )
//
//    }

    ConstraintLayout(modifier = Modifier.fillMaxSize().padding(20.dp)
    ) {
        val (image, text, fav) = createRefs()

        AssetImage(
            imageName =item.imageName,
            contentDescription = item.title,
            modifier = Modifier
                .size(300.dp)
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
        )
        Text(text=item.title,
            modifier = Modifier
                .fillMaxWidth()
                .background(Pink40)
                .padding(5.dp)
                .constrainAs(text) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color= Color.White
        )
        IconButton(onClick = {
            mainViewModel.insertItem(item.copy(isfav = !item.isfav))},
            modifier = Modifier.constrainAs(fav){
                top.linkTo(parent.top)
                end.linkTo(parent.end)
            })
        {
            Icon(imageVector = Icons.Default.Favorite,
                contentDescription ="Favorite",
                tint = if(item.isfav) MyRed else Gray,
                modifier = Modifier.clip(CircleShape)
                    .background(White)
                    .padding(5.dp))
        }
    }}


@Composable
fun AssetImage(imageName:String,contentDescription: String, modifier: Modifier){
    val context= LocalContext.current
    val assetManager=context.assets
    val inputStream=assetManager.open(imageName)
    val bitMap= BitmapFactory.decodeStream(inputStream)
    Image(bitmap = bitMap.asImageBitmap(),
        contentDescription =contentDescription,
        contentScale = ContentScale.Fit,

        modifier = modifier
    )
}