package com.example.smartagro.data.dataClasses

data class WeatherData(
    val Humidity: Int = 0,
    val Temperature: Int = 0,
    val RainPercent: Int = 0,
)

data class FarmData(
    val Temperature: Int = 0,
    val SoilMoisture: Int = 0,
    val SoilTemperature: Int = 0,
    val Humidity: Int = 0,
    val Stage: String = ""
)

// Main model to hold all the data from the root 'Kisan1' node
data class KisanData(
    val FarmData: FarmData = FarmData(),
    val IrrigationData: Any? = null, // You can make a class for this too
    val WeatherData: WeatherData = WeatherData()
)