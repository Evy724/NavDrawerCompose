package com.revature.navdrawercompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.revature.navdrawercompose.model.data.DrawerScreen
import com.revature.navdrawercompose.ui.theme.NavDrawerComposeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavDrawerComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AppMainScreen()
                }
            }
        }
    }
}
@Composable
fun AppMainScreen() {

    val navController = rememberNavController( )
    Surface(
        color = MaterialTheme.colors.background
    ) {

        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope() //coroutines
        val openDrawer = {
            scope.launch {
                drawerState.open()

            }
        }
        ModalDrawer(
            drawerState = drawerState,
            gesturesEnabled = drawerState.isOpen,
            drawerContent = {

                Drawer(onDestinationClicked = { route ->

                    scope.launch {

                        drawerState.close()

                    }

                    navController.navigate(route) {

                        popUpTo(navController.graph.startDestinationId) //replace this
                        launchSingleTop = true

                    }
                })
            }
        ) {

            NavHost(
                navController = navController,
                startDestination = DrawerScreen.Home.route
            ) {
                // 1st
                composable(DrawerScreen.Home.route) {

                    Home(openDrawer = {

                        openDrawer()

                    })
                }
                // 2nd
                composable(DrawerScreen.Account.route) {

                    Account(openDrawer = {

                        openDrawer()

                    })
                }
                // 3rd
                composable(DrawerScreen.Help.route) {

                    Help(navController = navController)

                }
            }
        }
    }
}