package com.nhathuy.tictactoe.intent

import com.nhathuy.tictactoe.model.GameMode
import com.nhathuy.tictactoe.model.TicTacToeState

sealed class TicTacToeIntent{
    data class SelectGameMode(val mode:GameMode) : TicTacToeIntent()
    data class CellClicked(val row:Int, val col:Int): TicTacToeIntent()
    object  ResetGame : TicTacToeIntent()
    object  BackToMenu : TicTacToeIntent()
}



