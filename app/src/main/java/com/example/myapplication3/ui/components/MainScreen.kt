package com.example.myapplication3.ui.components

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication3.ui.components.MainTopBar
import com.example.myapplication3.MainViewModel
import com.example.myapplication3.utils.DrawerEvents
import com.example.myapplication3.utils.IdArrayList
import com.example.myapplication3.utils.ListItem
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(mainViewModel: MainViewModel = hiltViewModel(), onClick: (ListItem) -> Unit) {

    var topBarTitle = rememberSaveable { mutableStateOf("Корги") }

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val mainList =mainViewModel.mainList
    LaunchedEffect(Unit) {
        mainViewModel.getAllItemsByCategory(topBarTitle.value)
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet() {
                DrawerMenu() { event ->
                    when (event) {
                        is DrawerEvents.OnItemClick -> {
                            topBarTitle.value = event.title
                            mainViewModel.getAllItemsByCategory(event.title)
                        }
                    }
                    scope.launch {
                        drawerState.close()
                    }
                }
            }
        },
        content = {
            Scaffold(
                topBar = {
                    MainTopBar(title = topBarTitle.value, drawerState = drawerState){
                        topBarTitle.value = "Избранные"
                        mainViewModel.getFavorites()
                    }
                }
            ) { innerPadding ->
                LazyColumn(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
                    items(mainList.value) { item ->
                        MainListItem(item = item) { listItem -> onClick(listItem) }
                    }
                }
            }
        }
    )
}

