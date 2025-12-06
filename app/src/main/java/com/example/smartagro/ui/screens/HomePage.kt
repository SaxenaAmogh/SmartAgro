package com.example.smartagro.ui.screens

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.smartagro.R
import com.example.smartagro.ui.components.LastIrrigatedBadge
import com.example.smartagro.ui.components.ProbeCapsule
import com.example.smartagro.ui.components.ProbeData
import com.example.smartagro.ui.components.WaterTank
import com.example.smartagro.ui.theme.Accent
import com.example.smartagro.ui.theme.Background
import com.example.smartagro.ui.theme.Primary
import com.example.smartagro.ui.theme.Secondary
import com.example.smartagro.ui.theme.SproutGreen
import com.example.smartagro.ui.theme.latoFontFamily
import java.time.Instant
import java.time.temporal.ChronoUnit

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomePage(navController: NavController){
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

    val sampleIrrigationTime = Instant.now().minus(2, ChronoUnit.HOURS)
    val probes: List<ProbeData> = listOf(
        ProbeData(1, 0.5f, 24),
        ProbeData(2, 0.60f, 23),
        ProbeData(3, 0.30f, 26),
        ProbeData(4, 0.85f, 22)
    )

    Scaffold(
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Background)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.TopCenter)
                    ){
                        item{
                            Box{
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(
                                            color = Primary,
                                            shape = RoundedCornerShape(
                                                bottomEnd = 35.dp,
                                                bottomStart = 35.dp
                                            )
                                        )
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .windowInsetsPadding(WindowInsets.systemBars.only(WindowInsetsSides.Top))
                                            .padding(
                                                horizontal = 0.05 * screenWidth
                                            )
                                    ){
                                        Spacer(modifier = Modifier.size(0.02 * screenHeight))
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Column() {
                                                Text(
                                                    text = "Namaste, Niranj",
                                                    color = Color.White,
                                                    fontSize = 28.sp,
                                                    fontFamily = latoFontFamily,
                                                    fontWeight = FontWeight.Bold,
                                                    modifier = Modifier
                                                )
                                                Text(
                                                    text = "Jorethang, Sikkim",
                                                    color = Secondary,
                                                    fontSize = 18.sp,
                                                    fontFamily = latoFontFamily,
                                                    modifier = Modifier
                                                )
                                            }
                                            Box(
                                                modifier = Modifier
                                                    .align(Alignment.CenterVertically)
                                                    .background(
                                                        color = Color(0xFFFFFFFF).copy(alpha = 0.12f),
                                                        shape = RoundedCornerShape(50.dp)
                                                    )
                                                    .border(
                                                        width = 1.dp,
                                                        color = Color.White.copy(alpha = 0.3f),
                                                        shape = RoundedCornerShape(50.dp)
                                                    )
                                                    .padding(8.dp)
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.Settings,
                                                    contentDescription = "settings_icon",
                                                    tint = Color.White,
                                                    modifier = Modifier
                                                        .size(36.dp)
                                                )
                                            }
                                        }
                                        Spacer(modifier = Modifier.size(0.03 * screenHeight))
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .background(
                                                    color = Color(0x0FFFFFFF).copy(alpha = 0.12f),
                                                    shape = RoundedCornerShape(20.dp)
                                                )
                                                .border(
                                                    width = 1.dp,
                                                    color = Color.White.copy(alpha = 0.3f),
                                                    shape = RoundedCornerShape(20.dp)
                                                )
                                                .padding(0.04 * screenWidth)
                                        ) {
                                            Row(
                                                modifier = Modifier.align(Alignment.CenterStart),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Icon(
                                                    painter = painterResource(R.drawable.raining),
                                                    contentDescription = "weather_icon",
                                                    tint = Color.Unspecified,
                                                    modifier = Modifier
                                                        .size(40.dp)
                                                )
                                                Spacer(modifier = Modifier.size(0.03 * screenWidth))
                                                Column(
                                                    verticalArrangement = Arrangement.Center
                                                ) {
                                                    Text(
                                                        text = "Weather Station",
                                                        color = Secondary,
                                                        fontSize = 16.sp,
                                                        fontFamily = latoFontFamily,
                                                        modifier = Modifier
                                                    )
                                                    Text(
                                                        text = "Rain Expected",
                                                        color = Color.White,
                                                        fontSize = 22.sp,
                                                        fontFamily = latoFontFamily,
                                                        fontWeight = FontWeight.Bold,
                                                        modifier = Modifier
                                                    )
                                                }
                                            }
                                            Column(
                                                horizontalAlignment = Alignment.End,
                                                modifier = Modifier.align(Alignment.CenterEnd)
                                            ) {
                                                Text(
                                                    text = "22°C",
                                                    color = Color.White,
                                                    fontSize = 28.sp,
                                                    fontFamily = latoFontFamily,
                                                    fontWeight = FontWeight.Bold,
                                                    modifier = Modifier
                                                )
                                                Text(
                                                    text = "Tap for details",
                                                    color = Secondary,
                                                    fontSize = 14.sp,
                                                    fontFamily = latoFontFamily,
                                                    modifier = Modifier
                                                )
                                            }
                                        }
                                        Spacer(modifier = Modifier.size(0.03 * screenHeight))
                                    }
                                }
                            }
                        }
                        item{
                            Spacer(modifier = Modifier.size(0.005 * screenHeight))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        horizontal = 0.04 * screenWidth,
                                        vertical = 0.005 * screenHeight
                                    )
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(
                                            color = Color(0xFFFFFFFF),
                                            shape = RoundedCornerShape(20.dp)
                                        )
                                        .border(
                                            width = 1.dp,
                                            color = Color.White.copy(alpha = 0.3f),
                                            shape = RoundedCornerShape(20.dp)
                                        )
                                        .padding(0.04 * screenWidth)
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = "Irrigation Status",
                                            color = Color.Black,
                                            fontSize = 20.sp,
                                            fontFamily = latoFontFamily,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier
                                        )
                                        Text(
                                            text = "System Idle",
                                            color = Accent,
                                            fontSize = 16.sp,
                                            fontFamily = latoFontFamily,
                                            modifier = Modifier
                                        )
                                    }
                                    Spacer(modifier = Modifier.size(0.015 * screenHeight))
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ){
                                        // --- COLUMN 1: WATER TANK ---
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            modifier = Modifier
                                                .weight(1f)
                                                .height(170.dp)
                                                .background(Accent.copy(alpha = 0.2f), RoundedCornerShape(20.dp))
                                                .padding(vertical = 16.dp) // Only vertical padding, let width fill
                                        ) {
                                            WaterTank(
                                                percentage = 0.69f,
                                                modifier = Modifier.width(120.dp).height(150.dp)
                                            )
                                        }

                                        // --- COLUMN 2: LAST IRRIGATED ---
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center,
                                            modifier = Modifier
                                                .weight(1f)
                                                .height(170.dp)
                                                .background(Color(0xFF57C9A3).copy(alpha = 0.4f), RoundedCornerShape(20.dp))
                                                .padding(6.dp)
                                        ) {
                                            LastIrrigatedBadge(
                                                timestamp = sampleIrrigationTime,
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .aspectRatio(1f)
                                            )
                                        }
                                    }
                                    Spacer(modifier = Modifier.size(0.01 * screenHeight))
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ){
                                        // --- COLUMN 1: WATER TANK ---
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            modifier = Modifier
                                                .weight(1f)
                                        ){
                                            Text(
                                                text = "69% Water Level",
                                                fontSize = 18.sp,
                                                fontFamily = latoFontFamily,
                                                color = Accent,
                                                fontWeight = FontWeight.Bold
                                            )
                                            Text(
                                                text = "Community Tank",
                                                color = Color(0xFFA1A7B0),
                                                fontSize = 16.sp,
                                                fontFamily = latoFontFamily,
                                                modifier = Modifier
                                            )
                                        }
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            modifier = Modifier
                                                .weight(1f)
                                        ){
                                            Text(
                                                text = "Last Irrigated",
                                                fontSize = 18.sp,
                                                fontFamily = latoFontFamily,
                                                color = Primary,
                                                fontWeight = FontWeight.Bold
                                            )
                                            Text(
                                                text = "Automated System",
                                                color = Color(0xFFA1A7B0),
                                                fontSize = 16.sp,
                                                fontFamily = latoFontFamily,
                                                modifier = Modifier
                                            )
                                        }
                                    }
                                }
                            }
                        }
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        horizontal = 0.04 * screenWidth,
                                        vertical = 0.005 * screenHeight
                                    )
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(
                                            color = Color(0xFFFFFFFF),
                                            shape = RoundedCornerShape(20.dp)
                                        )
                                        .border(
                                            width = 1.dp,
                                            color = Color.White.copy(alpha = 0.3f),
                                            shape = RoundedCornerShape(20.dp)
                                        )
                                        .padding(0.04 * screenWidth)
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = "Soil Data from Probes",
                                            color = Color.Black,
                                            fontSize = 20.sp,
                                            fontFamily = latoFontFamily,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier
                                        )
                                    }
                                    Spacer(modifier = Modifier.size(0.015 * screenHeight))
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.spacedBy(12.dp) // Gap between probes
                                    ) {
                                        probes.forEach { probe ->
                                            ProbeCapsule(
                                                data = probe,
                                                modifier = Modifier.weight(1f) // Equal width for all 4
                                            )
                                        }
                                    }
                                    Spacer(modifier = Modifier.size(0.01 * screenHeight))
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = "Probe 1",
                                            color = Primary,
                                            fontSize = 16.sp,
                                            fontFamily = latoFontFamily,
                                            textAlign = TextAlign.Center,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.weight(1f)
                                        )
                                        Text(
                                            text = "Probe 2",
                                            color = Primary,
                                            fontSize = 16.sp,
                                            fontFamily = latoFontFamily,
                                            textAlign = TextAlign.Center,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.weight(1f)
                                        )
                                        Text(
                                            text = "Probe 3",
                                            color = Primary,
                                            fontSize = 16.sp,
                                            fontFamily = latoFontFamily,
                                            textAlign = TextAlign.Center,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.weight(1f)
                                        )
                                        Text(
                                            text = "Probe 4",
                                            color = Primary,
                                            fontSize = 16.sp,
                                            fontFamily = latoFontFamily,
                                            textAlign = TextAlign.Center,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.weight(1f)
                                        )
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.size(0.3 * screenHeight))
                        }
                    }

                    // Bottom Navigation Bar
                    Row(
                        modifier = Modifier
                            .windowInsetsPadding(WindowInsets.systemBars.only(WindowInsetsSides.Bottom))
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                            .height(0.07 * screenHeight)
                            .padding(
                                horizontal = 0.065 * screenWidth
                            )
                            .background(
                                shape = RoundedCornerShape(40.dp),
                                color = SproutGreen
                            ),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = {},
                            modifier = Modifier
                                .clip(RoundedCornerShape(25.dp))
                                .size(45.dp)
                                .background(
                                    color = Primary,
                                    shape = RoundedCornerShape(50)
                                )
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.home),
                                contentDescription = "home",
                                Modifier.size(24.dp),
                                tint = Color.Black
                            )
                        }
                        Spacer(modifier = Modifier.size(8.dp))
                        IconButton(
                            onClick = {
                                navController.navigate("weather")
                            },
                            modifier = Modifier
                                .clip(RoundedCornerShape(50))
                                .size(45.dp)
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.weather_na),
                                contentDescription = "cart_na",
                                Modifier.size(32.dp),
                                tint = Accent
                            )
                        }
                        Spacer(modifier = Modifier.size(8.dp))
                        IconButton(
                            onClick = {
                                navController.navigate("security")
                            },
                            modifier = Modifier
                                .clip(RoundedCornerShape(50))
                                .size(45.dp)
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.harvest_na),
                                contentDescription = "explore",
                                Modifier.size(32.dp),
                                tint = Accent
                            )
                        }
                        Spacer(modifier = Modifier.size(8.dp))
                        IconButton(
                            onClick = {
                                navController.navigate("projects")
                            },
                            modifier = Modifier
                                .clip(RoundedCornerShape(50))
                                .size(45.dp)
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.wateringcan_na),
                                contentDescription = "cart_na",
                                Modifier.size(32.dp),
                                tint = Accent
                            )
                        }
                    }
                }
            }
        },
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showSystemUi = true)
@Composable
fun HomePagePreview(){
    HomePage(rememberNavController())
}