package com.nhathuy.tictactoe.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nhathuy.tictactoe.intent.TicTacToeIntent
import com.nhathuy.tictactoe.model.GameMode
import com.nhathuy.tictactoe.model.TicTacToeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TicTacToeViewModel:ViewModel() {
    private val _state = MutableStateFlow(TicTacToeState())
    val state: StateFlow<TicTacToeState> = _state

    fun setIntent(intent: TicTacToeIntent) {
        when (intent) {
            is TicTacToeIntent.SelectGameMode -> selectGameMode(intent.mode)
            is TicTacToeIntent.CellClicked -> handleCellClick(intent.row, intent.col)
            TicTacToeIntent.ResetGame -> resetGame()
            TicTacToeIntent.BackToMenu -> backToMenu()
        }
    }


    private fun handleCellClick(row: Int, col: Int) {
        _state.update { currentState ->
            if (currentState.board[row][col] == null && currentState.winner == null && !currentState.isDraw) {
                val newBoard = currentState.board.mapIndexed { i, rowList ->
                    if (i == row) {
                        rowList.mapIndexed { j, cell ->
                            if (j == col) currentState.currentPlayer else cell
                        }
                    } else {
                        rowList
                    }
                }
                val newPlayer = if (currentState.currentPlayer == "X") "O" else "X"
                val winner = checkWinner(newBoard)
                val isDraw = if (winner == null) checkDraw(newBoard) else false

                if (currentState.gameMode == GameMode.PVE && winner == null && !isDraw && newPlayer == "O") {
                    viewModelScope.launch {
                        val aiMove = getAIMove(newBoard)
                        handleCellClick(aiMove.first, aiMove.second)
                    }
                }

                currentState.copy(
                    board = newBoard,
                    currentPlayer = newPlayer,
                    winner = winner,
                    isDraw = isDraw
                )
            } else {
                currentState
            }
        }
    }

    private fun getAIMove(board: List<List<String?>>): Pair<Int, Int> {
        var bestScore = Double.NEGATIVE_INFINITY
        var bestMove: Pair<Int, Int>? = null


        for (i in board.indices) {
            for (j in board[i].indices) {
                if (board[i][j] == null) {
                    val newBoard = board.map {
                        it.toMutableList()
                    }
                    val score = minmax(
                        newBoard,
                        0,
                        true,
                        Double.NEGATIVE_INFINITY,
                        Double.POSITIVE_INFINITY
                    )
                    if (score > bestScore) {
                        bestScore = score
                        bestMove = Pair(i, j)
                    }
                }
            }
        }
        return bestMove ?: Pair(0, 0)
    }


    private fun checkDraw(board: List<List<String?>>): Boolean {
        return board.all { row ->
            row.all {
                it != null
            }
        }
    }

    private fun checkWinner(board: List<List<String?>>): String? {
        val lines = listOf(
            listOf(0 to 0, 0 to 1, 0 to 2),
            listOf(1 to 0, 1 to 1, 1 to 2),
            listOf(2 to 0, 2 to 1, 2 to 2),
            listOf(0 to 0, 1 to 0, 2 to 0),
            listOf(0 to 1, 1 to 1, 2 to 1),
            listOf(0 to 2, 1 to 2, 2 to 2),
            listOf(0 to 0, 1 to 1, 2 to 2),
            listOf(0 to 2, 1 to 1, 2 to 0),
        )
        for (line in lines) {
            val (a, b, c) = line
            if (board[a.first][a.second] != null
                && board[a.first][a.second] == board[b.first][b.second] &&
                board[a.first][a.second] == board[c.first][c.second]
            ) {
                return board[a.first][a.second]
            }
        }
        return null
    }

    private fun backToMenu() {
        _state.value = TicTacToeState()
    }

    private fun resetGame() {
        _state.update {
            it.copy(
                board = List(3) {
                    List(3) {
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

    private fun minmax(
        board: List<List<String?>>,
        depth: Int,
        isMaximizing: Boolean,
        alpha: Double,
        beta: Double): Double {
        val winner = checkWinner(board)
        if (winner == "O") return 10.0 - depth
        if (winner == "X") return 10.0 + depth
        if (checkDraw(board))
            return 0.0

        var alphaCopy = alpha
        var betaCopy = beta

        if (isMaximizing) {
            var bestScore = Double.NEGATIVE_INFINITY
            for (i in board.indices) {
                for (j in board[i].indices) {
                    if (board[i][j] == null) {
                        val newBoard = board.map {
                            it.toMutableList()
                        }
                        newBoard[i][j] = "O"
                        val score = minmax(newBoard, depth - 1, false, alphaCopy, betaCopy)
                        bestScore = maxOf(bestScore, score)
                        alphaCopy = maxOf(alphaCopy, bestScore)
                        if (betaCopy <= alphaCopy) break
                    }
                }

            }
            return bestScore
        } else {
            var bestScore = Double.NEGATIVE_INFINITY
            for (i in board.indices) {
                for (j in board[i].indices) {
                    if (board[i][j] == null) {
                        val newBoard = board.map {
                            it.toMutableList()
                        }
                        newBoard[i][j] = "X"
                        val score = minmax(newBoard, depth + 1, true, alphaCopy, betaCopy)
                        bestScore = minOf(bestScore, score)
                        alphaCopy = minOf(alphaCopy, bestScore)
                        if (betaCopy <= alphaCopy) break
                    }
                }
            }
            return bestScore
        }
    }
}