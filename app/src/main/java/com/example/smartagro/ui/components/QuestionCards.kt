package com.example.smartagro.ui.components

import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize

@Composable
fun SimpleFourItemDropdown() {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("Select an Option") }
    val menuItems = listOf("Maize", "Sikkim Orange", "Wheat", "Cardamom")

    Box(
        modifier = Modifier
            .wrapContentSize(Alignment.TopStart)
            .padding(16.dp)
    ) {
        // This is the component that the user clicks to open the menu

    }
}