package com.revature.navdrawercompose.ui

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun TopBar(title:String, buttonIcon: ImageVector,onButtonClicked:()->Unit)
{
    TopAppBar(
        title = { Text(text = title) },
        backgroundColor = MaterialTheme.colors.primaryVariant,
        navigationIcon={ IconButton(onClick = { onButtonClicked() }) {
            Icon(buttonIcon,contentDescription = "")
        }}
    )
}
