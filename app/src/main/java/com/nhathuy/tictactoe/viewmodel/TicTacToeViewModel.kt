package com.nhathuy.tictactoe.viewmodel

import androidx.lifecycle.ViewModel
import com.nhathuy.tictactoe.model.TicTacToeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TicTacToeViewModel:ViewModel() {
    private val _state= MutableStateFlow(TicTacToeState())
    val state:StateFlow<TicTacToeState> = _state
}