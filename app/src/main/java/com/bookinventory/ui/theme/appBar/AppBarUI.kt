package com.bookinventory.ui.theme.appBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.bookinventory.R

/*
* App bars for different pages
* Might have different functionalities so can create different app bar for different usage
*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPageAppBar(title: String, onDrawerClick: () -> Unit) {
    TopAppBar(title = {
        Text(text = title)
    },
        navigationIcon = {
            IconButton(onClick = onDrawerClick) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = R.string.open_drawer.toString()
                )
            }
        })
}

@Preview
@Composable
fun Preview() {
    MainPageAppBar(title = "Book Inventory") {
    }
}