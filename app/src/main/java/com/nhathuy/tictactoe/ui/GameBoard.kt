package com.nhathuy.tictactoe.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.unit.dp
import com.nhathuy.tictactoe.intent.TicTacToeIntent
import com.nhathuy.tictactoe.model.TicTacToeState

@Composable
fun GameBoard(state: TicTacToeState, onIntent: (TicTacToeIntent) ->  Unit) {

    val navyBlue= Color(0xFF000080)
    Surface(modifier = Modifier
        .aspectRatio(1f)
        .padding(16.dp),
        shape = RoundedCornerShape(8.dp),
        color = Color.White,
        shadowElevation = 4.dp
    ) {
        Box{
            Canvas(modifier = Modifier.fillMaxSize()){
                val strokeWidth= 2.dp.toPx()

                drawLine(
                    color = navyBlue,
                    start = Offset(size.width / 3, 0f),
                    end = Offset(size.width / 3, size.height),
                    strokeWidth = strokeWidth,
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                )
                drawLine(
                    color = navyBlue,
                    start = Offset(2*size.width / 3, 0f),
                    end = Offset(2*size.width / 3, size.height),
                    strokeWidth = strokeWidth,
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                )
                drawLine(
                    color = navyBlue,
                    start = Offset(0f, size.height/3),
                    end = Offset(size.width , size.height/ 3),
                    strokeWidth = strokeWidth,
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                )
                drawLine(
                    color = navyBlue,
                    start = Offset(0f, 2*size.height/3),
                    end = Offset(size.width,2*size.height/3),
                    strokeWidth = strokeWidth,
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                )
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                for (i in 0..2) {
                    Row(
                        modifier = Modifier.weight(1f),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        for (j in 0..2) {
                            Box(modifier = Modifier.weight(1f)) {
                                BoardCell(
                                    value = state.board[i][j],
                                    onClick = {
                                        onIntent(TicTacToeIntent.CellClicked(i, j))
                                    },
                                    enabled = state.board[i][j] == null && state.winner == null && !state.isDraw
                                )
                            }
                        }
                    }
                }
            }


        }
    }
}

