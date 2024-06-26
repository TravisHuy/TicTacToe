package com.nhathuy.tictactoe.viewmodel

import androidx.lifecycle.ViewModel
import com.nhathuy.tictactoe.intent.TicTacToeIntent
import com.nhathuy.tictactoe.model.GameMode
import com.nhathuy.tictactoe.model.TicTacToeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class TicTacToeViewModel:ViewModel() {
    private val _state= MutableStateFlow(TicTacToeState())
    val state:StateFlow<TicTacToeState> = _state

    fun setIntent(intent:TicTacToeIntent){
        when(intent){
            is TicTacToeIntent.SelectGameMode -> selectGameMode(intent.mode)
            is TicTacToeIntent.CellClicked -> handleCellClick(intent.row,intent.col)
            TicTacToeIntent.ResetGame -> resetGame()
            TicTacToeIntent.BackToMenu -> backToMenu()
        }
    }



    private fun handleCellClick(row: Int, col: Int) {

    }

    private fun backToMenu() {
        _state.value = TicTacToeState()
    }

    private fun resetGame() {
        _state.update {
            it.copy(
                board = List(3) {
                    List(3){
                        null
                    }
                },
                currentPlayer = "X",
                winner = null,
                isDraw = false
            )
        }
    }

    private fun selectGameMode(mode: GameMode) {
        _state.update {
            it.copy(gameMode = mode)
        }
    }
}