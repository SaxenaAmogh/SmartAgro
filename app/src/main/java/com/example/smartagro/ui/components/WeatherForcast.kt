package com.example.smartagro.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.smartagro.R

data class ForecastItem(
    val day: String,
    val weatherCondition: String,
    val icon: Painter,
    val iconColor: Color,
    val highTemp: Int,
    val lowTemp: Int
)

@Composable
fun ForecastCard() {
    // 2. Mock Data based on your image
    val forecastData = listOf(
        ForecastItem("Today", "Rainy", painterResource(R.drawable.raining), Color.Gray, 22, 18),
        ForecastItem("Tomorrow", "Sunny", painterResource(R.drawable.sunny), Color(0xFFFFB74D),22, 18), // Orange
        ForecastItem("Wed", "Haze", painterResource(R.drawable.haze), Color(0xFFFFB74D), 27, 21)
    )

    // 3. Main Card
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(24.dp),
                spotColor = Color.LightGray.copy(alpha = 0.4f)
            ),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            // Header Row: Icon + Title
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 24.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.DateRange,
                    contentDescription = "Forecast",
                    tint = Color(0xFF4285F4), // Google Blue-ish
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Forecast",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2d3436)
                )
            }

            // List Items
            forecastData.forEachIndexed { index, item ->
                ForecastRow(item)

                // Add spacer between items, but not after the last one
                if (index < forecastData.size - 1) {
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}

@Composable
fun ForecastRow(item: ForecastItem) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 1. Day (Left aligned)
        // Weight 1f ensures it takes up consistent space
        Text(
            text = item.day,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF455A64), // Dark Grey
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(55.dp))
        // 2. Condition (Center aligned mostly)
        // We use a Row here to group the icon and text tightly
        Row(
            modifier = Modifier.weight(1.5f), // Slightly more weight for the middle section
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                item.icon,
                contentDescription = item.weatherCondition,
                tint = item.iconColor,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = item.weatherCondition,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }

        // 3. Temp (Right aligned)
        Text(
            text = "${item.highTemp}° / ${item.lowTemp}°",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.weight(1f),
            textAlign = androidx.compose.ui.text.style.TextAlign.End
        )
    }
}