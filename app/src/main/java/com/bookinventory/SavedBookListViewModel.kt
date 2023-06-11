package com.bookinventory

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotApplyResult
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bookinventory.async.Async
import com.bookinventory.model.Book
import com.bookinventory.repository.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

data class SavedBookListUIState(
    val bookList: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)

class SavedBookListViewModel(private val _bookRepository: BookRepository) : ViewModel() {
    private val _bookAsync = _bookRepository.getBookList().map {
        Async.Success(data = it)
    }.catch<Async<List<Book>>> {
        emit(Async.Error(it.toString()))
    }

    val uiState: StateFlow<SavedBookListUIState> = _bookAsync.map {
        when (it) {
            Async.Loading -> {
                SavedBookListUIState(isLoading = true)
            }
            is Async.Error -> {
                SavedBookListUIState(
                    errorMessage = it.error
                )
            }
            is Async.Success -> {
                SavedBookListUIState(
                    bookList = it.data
                )
            }
        }
    }.stateIn(
            initialValue = SavedBookListUIState(isLoading = true),
    scope = viewModelScope,
    started = SharingStarted.WhileSubscribed(5000)
    )

}