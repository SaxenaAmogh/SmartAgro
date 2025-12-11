package com.example.smartagro.data.dataClasses

data class WeatherData(
    // NOTE: Using Double for better compatibility with potential float values,
    // even if the image shows them as integers.
    val Humidity: Double = 0.0,
    val Temperature: Double = 0.0,
    val RainPercent: Double = 0.0,
    val WindSpeed: Double = 0.0
)

data class Node1Data( // Represents the sensor data inside 'Node1'
    val Temperature: Double = 0.0,
    val SoilMoisture: Double = 0.0,
    val SoilTemperature: Double = 0.0, // Renamed from SoilTemperature to match the database key SoilTemp
    val Humidity: Double = 0.0,
)

data class FarmDataContainer( // Represents the parent of Node1 (i.e., the original 'FarmData' node)
    val Node1: Node1Data = Node1Data(),
    val Node2: Node1Data = Node1Data(),
    val Node3: Node1Data = Node1Data(),
    val Node4: Node1Data = Node1Data(),
    // You can add Node2, Node3, etc., here if needed: val Node2: Node1Data = Node1Data()
)

data class KisanData(
    // New top-level fields requested by you (Using default values for safety)
    val FarmSizeAcres: Double = 0.0,
    val Latitude: Double = 0.0, // Matches the key in your image
    val Longitude: Double = 0.0, // New field for sending data
    val Location: String = "", // Matches the key in your image
    val Crop: String = "",
    val FarmType: String = "",
    val Name: String = "",
    val IrrigationStatus: Boolean = false,
    val Valve1: Boolean = false,
    val Valve2: Boolean = false,

    // Nested structures
    val FarmData: FarmDataContainer = FarmDataContainer(), // Points to the Node1 holder
    val WeatherData: WeatherData = WeatherData(),
    val IrrigationData: Any? = null, // Kept this as you had it
)