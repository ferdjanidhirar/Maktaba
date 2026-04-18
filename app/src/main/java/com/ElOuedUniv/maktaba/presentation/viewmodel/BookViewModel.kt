package com.ElOuedUniv.maktaba.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ElOuedUniv.maktaba.data.model.Book
import com.ElOuedUniv.maktaba.domain.usecase.GetBooksUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.update
import com.ElOuedUniv.maktaba.domain.usecase.AddBookUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
/**
 * ViewModel for managing book-related UI state
 * This follows the MVVM pattern where ViewModel acts as a bridge between
 * the UI and the business logic (Use Cases)
 */
class BookViewModel(
    private val getBooksUseCase: GetBooksUseCase,
    private val addBookUseCase: AddBookUseCase
) : ViewModel() {

    // Private mutable state for internal use
  private val _uiState = MutableStateFlow(BookUiState())
    val uiState: StateFlow<BookUiState> = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<BookUiEvent>()
    val uiEvent: SharedFlow<BookUiEvent> = _uiEvent
    init {
        // Load books when ViewModel is created
        loadBooks()
    }

    /**
     * Load all books from the use case
     */
    private fun loadBooks() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            getBooksUseCase()
                .catch { e ->
                    _uiState.update { it.copy(isLoading = false, errorMessage = e.message) }
                }
                .collect { bookList ->
                    _uiState.update { it.copy(books = bookList, isLoading = false) }
            }
        }
    }

    /**
     * Refresh the books list
     * Can be called from UI to reload data
     */
  private fun addBook(title: String, isbn: String, nbPages: Int) {
        viewModelScope.launch {
            val newBook = Book(isbn = isbn, title = title, nbPages = nbPages)
            addBookUseCase(newBook)
            _uiState.update { it.copy(isAddingBook = false) }
            _uiEvent.emit(BookUiEvent.ShowSnackbar("Book added successfully!"))
        }
    }
}
