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
import com.nhathuy.tictactoe.model.GameMode

@Composable
fun GameModeSelection(onSelectMode: (GameMode) -> Unit){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Select Game Mode", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { onSelectMode(GameMode.PVP) }) {
            Text(text = "Player vs Player")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick ={onSelectMode(GameMode.PVE)}) {
            Text(text = "Player vs AI")
        }
    }
}