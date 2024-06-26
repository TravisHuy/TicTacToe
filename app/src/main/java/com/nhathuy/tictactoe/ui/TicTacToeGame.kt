package com.nhathuy.tictactoe.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nhathuy.tictactoe.intent.TicTacToeIntent
import com.nhathuy.tictactoe.model.GameMode
import com.nhathuy.tictactoe.viewmodel.TicTacToeViewModel


@Composable
fun TicTacGame(viewModel: TicTacToeViewModel = viewModel()){

    val state by viewModel.state.collectAsState()
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        when(state.gameMode){
            GameMode.MENU -> GameModeSelection{
                viewModel.setIntent(TicTacToeIntent.SelectGameMode(it))
            }
            GameMode.PVP, GameMode.PVE ->{
                GameInfo(state, viewModel::setIntent)
                Spacer(modifier = Modifier.height(16.dp))
                GameBoard(state,viewModel::setIntent)
            }
        }
    } 
}



