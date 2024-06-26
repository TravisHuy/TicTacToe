package com.nhathuy.tictactoe.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun BoardCell(value: String?, onClick: () -> Unit, enabled: Boolean) {
    Button(onClick = onClick,
        modifier = Modifier
            .size(100.dp)
            .padding(4.dp),
        enabled=enabled) {
        Text(text = value ?: "",
        style = MaterialTheme.typography.headlineMedium)

    }
}