package com.example.smartagro.ui.screens

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.smartagro.R
import com.example.smartagro.ui.theme.Accent
import com.example.smartagro.ui.theme.Primary
import com.example.smartagro.ui.theme.latoFontFamily

@RequiresPermission(Manifest.permission.ACCESS_FINE_LOCATION)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectCropPage(navController: NavController) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val context = LocalContext.current

    val focusManager = LocalFocusManager.current
    val view = LocalView.current
    val window = (view.context as? Activity)?.window
    val windowInsetsController = window?.let { WindowCompat.getInsetsController(it, view) }

    if (windowInsetsController != null) {
        windowInsetsController.isAppearanceLightStatusBars = true
    }

    var name by remember { mutableStateOf("") }
    var locationText by remember { mutableStateOf("No location selected") }
    var latLng by remember { mutableStateOf<Pair<Double, Double>?>(null) }
    var loadingLocation by remember { mutableStateOf(false) }

    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf("Select a Crop") }
    val items = listOf("Maize", "Sikkim Orange", "Wheat", "Cardamom", "Select a Crop")
    var farmType by remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.TopCenter)
                            .padding(
                                horizontal = 0.035 * screenWidth
                            )
                    ) {
                        item {
                            Column(
                                modifier = Modifier
                                    .windowInsetsPadding(
                                        WindowInsets.systemBars.only(
                                            WindowInsetsSides.Top
                                        )
                                    )
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .align(Alignment.CenterStart)
                                            .clickable {
                                                navController.popBackStack()
                                            }
                                            .background(
                                                color = Accent.copy(alpha = 0.12f),
                                                shape = RoundedCornerShape(50.dp)
                                            )
                                            .border(
                                                width = 1.dp,
                                                color = Color.White.copy(alpha = 0.3f),
                                                shape = RoundedCornerShape(50.dp)
                                            )
                                            .padding(6.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                            contentDescription = "settings_icon",
                                            tint = Primary,
                                            modifier = Modifier
                                                .size(30.dp)
                                        )
                                    }
                                    Spacer(modifier = Modifier.size(0.03 * screenWidth))
                                    Text(
                                        text = "Select Your Crop",
                                        color = Primary,
                                        fontFamily = latoFontFamily,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 30.sp,
                                        modifier = Modifier
                                            .align(Alignment.Center)
                                    )
                                }
                            }
                        }
                        item {
                            Spacer(modifier = Modifier.size(0.03 * screenHeight))
                            OutlinedTextField(
                                value = name,
                                onValueChange = {
                                    name = it
                                },
                                placeholder = {
                                    Text(
                                        "Enter Your Name",
                                        color = Accent,
                                        fontWeight = FontWeight.W500,
                                        fontSize = 20.sp
                                    )
                                },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(size = 16.dp),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = Primary,
                                    unfocusedBorderColor = Primary,
                                    focusedLabelColor = Primary,
                                    cursorColor = Primary,
                                    focusedTextColor = Accent,
                                    unfocusedTextColor = Accent,
                                ),
                                textStyle = TextStyle(
                                    fontSize = 20.sp,
                                    color = Accent,
                                    fontWeight = FontWeight.W500
                                ),
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        focusManager.clearFocus()
                                    }
                                ),
                                singleLine = true,
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Done
                                ),
                            )
                        }
                        item {
                            Spacer(modifier = Modifier.size(0.02 * screenHeight))
                            ExposedDropdownMenuBox(
                                expanded = expanded,
                                onExpandedChange = { expanded = !expanded }
                            ) {
                                OutlinedTextField(
                                    value = selectedItem,
                                    onValueChange = { },
                                    readOnly = true,
                                    trailingIcon = {
                                        ExposedDropdownMenuDefaults.TrailingIcon(
                                            expanded
                                        )
                                    },
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = Primary,
                                        unfocusedBorderColor = Primary,
                                        focusedLabelColor = Primary,
                                        cursorColor = Primary,
                                        focusedTextColor = Accent,
                                        unfocusedTextColor = Accent,
                                    ),
                                    textStyle = TextStyle(
                                        fontSize = 20.sp,
                                        color = Accent,
                                        fontWeight = FontWeight.W500
                                    ),
                                    shape = RoundedCornerShape(16.dp),
                                    modifier = Modifier
                                        .menuAnchor()
                                        .fillMaxWidth()
                                )
                                ExposedDropdownMenu(
                                    expanded = expanded,
                                    onDismissRequest = { expanded = false }
                                ) {
                                    items.forEach { item ->
                                        DropdownMenuItem(
                                            text = { Text(item, fontSize = 20.sp) },
                                            onClick = {
                                                selectedItem = item
                                                expanded = false
                                            }
                                        )
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.size(0.015 * screenHeight))
                            if (selectedItem == "Select a Crop") {
                                // no image
                            } else {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .border(
                                            width = 3.dp,
                                            color = Primary,
                                            shape = RoundedCornerShape(20.dp)
                                        )
                                        .height(0.3 * screenHeight),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                        painter = painterResource(
                                            when (selectedItem) {
                                                "Maize" -> R.drawable.maize
                                                "Sikkim Orange" -> R.drawable.orange
                                                "Wheat" -> R.drawable.wheat
                                                "Cardamom" -> R.drawable.cardamom
                                                else -> R.drawable.white
                                            }
                                        ),
                                        contentDescription = "Select Crop Image",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                    )
                                }
                                Spacer(modifier = Modifier.size(0.02 * screenHeight))
                            }
                        }
                        item {
                            Text(
                                text = "Select Farm Type",
                                color = Accent,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = latoFontFamily,
                            )
                            Spacer(modifier = Modifier.size(0.015 * screenHeight))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                            ) {
                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                        .border(
                                            width = 3.dp,
                                            color = if (farmType == "Terrace") Primary else Color(
                                                0xFFDADADA
                                            ),
                                            shape = RoundedCornerShape(20.dp)
                                        )
                                        .clip(RoundedCornerShape(20.dp))
                                        .height(0.25 * screenHeight)
                                        .clickable(
                                            onClick = {
                                                farmType = "Terrace"
                                            }
                                        ),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Image(
                                        painter = painterResource(R.drawable.terrace),
                                        contentDescription = "Select Crop Image",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(16.dp))
                                            .fillMaxWidth()
                                            .height(0.21 * screenHeight)
                                    )
                                    Text(
                                        text = "Terrace",
                                        color = Color.Black,
                                        fontSize = 28.sp,
                                        fontWeight = FontWeight.W500,
                                        modifier = Modifier
                                            .align(Alignment.CenterHorizontally)
                                    )
                                }
                                Spacer(modifier = Modifier.size(0.015 * screenWidth))
                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                        .border(
                                            width = 3.dp,
                                            color = if (farmType == "Flat Land") Primary else Color(
                                                0xFFDADADA
                                            ),
                                            shape = RoundedCornerShape(20.dp)
                                        )
                                        .clip(RoundedCornerShape(20.dp))
                                        .height(0.25 * screenHeight)
                                        .clickable(
                                            onClick = {
                                                farmType = "Flat Land"
                                            }
                                        ),
                                ) {
                                    Image(
                                        painter = painterResource(R.drawable.farming),
                                        contentDescription = "Select Crop Image",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(16.dp))
                                            .fillMaxWidth()
                                            .height(0.21 * screenHeight)
                                    )
                                    Text(
                                        text = "Flat Land",
                                        color = Color.Black,
                                        fontSize = 28.sp,
                                        fontWeight = FontWeight.W500,
                                        modifier = Modifier
                                            .align(Alignment.CenterHorizontally)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}

@RequiresPermission(Manifest.permission.ACCESS_FINE_LOCATION)
@Preview(showSystemUi = true)
@Composable
fun SelectCropPagePreview() {
    SelectCropPage(rememberNavController())
}
