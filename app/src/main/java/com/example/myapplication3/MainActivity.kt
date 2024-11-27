package com.example.myapplication3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication3.ui.components.InfoScreen
import com.example.myapplication3.ui.components.MainScreen
import com.example.myapplication3.ui.theme.MyApplication3Theme
import com.example.myapplication3.utils.ItemSaver
import com.example.myapplication3.utils.ListItem
import com.example.myapplication3.utils.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var item= rememberSaveable(stateSaver = ItemSaver) {
                mutableStateOf( ListItem(id = 0, title = "", imageName = "",
                    htmlName = "", isfav = false, category = ""))
            }
            val navController = rememberNavController()
            MyApplication3Theme {
                NavHost(
                    navController = navController,
                    startDestination = Routes.MAIN_SCREEN.route
                ) {
                    composable(Routes.MAIN_SCREEN.route) {
                        MainScreen() {  listItem ->item.value =ListItem(listItem.id,
                            listItem.title,
                            listItem.imageName,
                            listItem.htmlName,
                            listItem.isfav,
                            listItem.category)
                            navController.navigate(Routes.INFO_SCREEN.route)
                        }
                    }
                    composable(Routes.INFO_SCREEN.route) {
                        InfoScreen(item = item.value!!)

                    }
                }
            }
        }
    }
}