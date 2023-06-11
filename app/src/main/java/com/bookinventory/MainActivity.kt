package com.bookinventory

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bookinventory.application.BookApplication
import com.bookinventory.ui.theme.BookInventoryTheme
import com.bookinventory.ui.theme.appBar.MainPageAppBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookInventoryTheme {
                // A surface container using the 'background' color from the theme
                BookInventoryApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookInventoryApp() {
    val snackBarHostState: SnackbarHostState by remember { mutableStateOf(SnackbarHostState()) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(3.dp), topBar = {
            MainPageAppBar(title = "Book Inventory") {
            }
        }, snackbarHost = { SnackbarHost(hostState = snackBarHostState) }) { padding ->
        val application: BookApplication =
            LocalContext.current.applicationContext as BookApplication
        val viewModel: SavedBookListViewModel = viewModel(factory = BookViewModelProviderFactory {
            SavedBookListViewModel(application.bookRepository)
        })
        val uiState: SavedBookListUIState by viewModel.uiState.collectAsStateWithLifecycle()
        if (uiState.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "loading...")
            }
        }
        else if (uiState.bookList.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "No data")
            }
        }
        else {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                columns = GridCells.Fixed(2),
            ) {
                val bookList = uiState.bookList
                items(bookList.count()) {
                    Card(
                        modifier = Modifier
                            .padding(5.dp)
                            .height(200.dp)
                            .padding(3.dp),
                        border = BorderStroke(2.dp, Color.Black),
                        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(3.dp), contentAlignment = Alignment.Center
                        ) {
                            Text(
                                textAlign = TextAlign.Center,
                                text = bookList[it].bookName
                            )
                        }

                    }
                }
            }

        }

        // Show error message if any
        LaunchedEffect(key1 = uiState.errorMessage) {
            uiState.errorMessage?.let {
                snackBarHostState.showSnackbar(it)
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    BookInventoryApp()
}