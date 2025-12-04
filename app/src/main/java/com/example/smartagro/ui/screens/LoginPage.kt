package com.example.smartagro.ui.screens

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
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
import com.example.smartagro.ui.theme.Secondary
import com.example.smartagro.ui.theme.Surface
import com.example.smartagro.ui.theme.latoFontFamily

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginPage(navController: NavController) {
    // Implementation of the Login Page UI goes here
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val context = LocalContext.current

    var mobileNumber by remember { mutableStateOf("") }

    val focusManager = LocalFocusManager.current
    val view = LocalView.current
    val window = (view.context as? Activity)?.window
    val windowInsetsController = window?.let { WindowCompat.getInsetsController(it, view) }

    if (windowInsetsController != null) {
        windowInsetsController.isAppearanceLightStatusBars = true
    }

    Scaffold(
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Surface)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .windowInsetsPadding(WindowInsets.systemBars.only(WindowInsetsSides.Top))
                        .padding(
                            horizontal = 0.035 * screenWidth
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "Login Background",
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(20))
                                .size(0.3 * screenWidth)
                        )
                        Spacer(modifier = Modifier.size(0.01 * screenHeight))
                        Text(
                            text = "Welcome to",
                            color = Primary,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.W400,
                            fontFamily = latoFontFamily,
                        )
                        Text(
                            text = "AgroSmart",
                            color = Primary,
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = latoFontFamily,
                        )
                        Spacer(modifier = Modifier.size(0.02 * screenHeight))
                        Text(
                            text = "Your Smart Agriculture Companion",
                            color = Accent,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = latoFontFamily,
                        )
                        Spacer(modifier = Modifier.size(0.07 * screenHeight))
                        OutlinedTextField(
                            value = mobileNumber,
                            onValueChange = { newText ->
                                if (newText.length <= 10 && newText.all { it.isDigit() }) {
                                    mobileNumber = newText
                                }
                            },
                            placeholder = { Text("Enter Mobile No.", color = Secondary) },
                            label = { Text("Mobile No.", color = Primary) },
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
                            // INCREASE FONT SIZE HERE
                            textStyle = LocalTextStyle.current.copy(
                                fontSize = 18.sp // Default is usually 16sp, increased by 2
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    focusManager.clearFocus()
                                }
                            ),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done // Ensures the checkmark appears on keyboard
                            ),
                        )
                        Spacer(modifier = Modifier.size(0.02 * screenHeight))
                        FloatingActionButton(
                            onClick = {
                                navController.navigate("home")
                            },
                            modifier = Modifier
                                .fillMaxWidth(),
                            containerColor = Primary,
                            elevation = FloatingActionButtonDefaults.elevation(
                                defaultElevation = 0.dp,
                                pressedElevation = 0.dp,
                                focusedElevation = 0.dp,
                                hoveredElevation = 0.dp
                            )
                        ) {
                            Text(
                                text = "Get OTP",
                                fontFamily = latoFontFamily,
                                color = Color.White,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.W500,
                            )
                        }
                        Spacer(modifier = Modifier.size(0.2 * screenHeight))
                    }
                }
            }
        }
    )
}

@Preview(showSystemUi = true)
@Composable
fun LoginPagePreview() {
    LoginPage(rememberNavController())
}