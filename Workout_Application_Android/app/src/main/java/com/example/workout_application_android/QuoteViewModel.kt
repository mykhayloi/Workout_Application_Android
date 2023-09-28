package com.example.workout_application_android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuoteViewModel: ViewModel() {

    val quotes: MutableList<List<Quote>> = mutableListOf()

    fun getData() {
        val client: QuoteService = QuoteService.client
        viewModelScope.launch(Dispatchers.IO) {
            var quote = client.getQuotes()
            quotes.add(quote)
        }
    }

}