package com.nhathuy.tictactoe.model

data class TicTacToeState(
    val board: List<List<String?>> = List(3){
        List(3){
            null
        }
    },
    val currentPlayer: String ="X",
    val winner:String?=null,
    val isDraw:Boolean= false
)
