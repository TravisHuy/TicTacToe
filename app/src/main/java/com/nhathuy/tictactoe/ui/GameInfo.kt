package com.nhathuy.tictactoe.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nhathuy.tictactoe.intent.TicTacToeIntent
import com.nhathuy.tictactoe.model.TicTacToeState

@Composable
fun GameInfo(state: TicTacToeState, onIntent: (TicTacToeIntent)-> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Current Player:  ${ state.currentPlayer }",
            style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))
        when{
        state.winner !=null ->
            Text(text = "Winner: ${state.winner}",
            style = MaterialTheme.typography.headlineMedium)

            state.isDraw -> Text(
                text = "It's a draw ",
                style = MaterialTheme.typography.headlineMedium)
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { onIntent(TicTacToeIntent.ResetGame) }) {
            Text(text = "Reset Game")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { onIntent(TicTacToeIntent.BackToMenu) }) {
            Text(text = "Back to Menu")
        }
    }

}