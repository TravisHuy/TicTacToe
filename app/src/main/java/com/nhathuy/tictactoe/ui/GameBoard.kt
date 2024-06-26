package com.nhathuy.tictactoe.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import com.nhathuy.tictactoe.intent.TicTacToeIntent
import com.nhathuy.tictactoe.model.TicTacToeState

@Composable
fun GameBoard(state: TicTacToeState, onIntent: (TicTacToeIntent) ->  Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        for (i in 0..2){
            Row {
                for (j in 0..2){
                    key(i*3+j) {
                        BoardCell(
                            value= state.board[i][j],
                            onClick = {
                                onIntent(TicTacToeIntent.CellClicked(i,j))
                            },
                            enabled= state.board[i][j]==null && state.winner == null && !state.isDraw
                        )
                    }
                }
            }
        }
    }
}

