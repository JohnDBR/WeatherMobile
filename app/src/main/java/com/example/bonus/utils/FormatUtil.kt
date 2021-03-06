package com.example.bonus.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

class FormatUtil {

    companion object {
        @JvmStatic
        private val ICON_URLS = hashMapOf(
            // Day Icons
            "01d" to "https://openweathermap.org/img/wn/01d@2x.png",
            "02d" to "https://openweathermap.org/img/wn/02d@2x.png",
            "03d" to "https://openweathermap.org/img/wn/03d@2x.png",
            "04d" to "https://openweathermap.org/img/wn/04d@2x.png",
            "09d" to "https://openweathermap.org/img/wn/09d@2x.png",
            "10d" to "https://openweathermap.org/img/wn/10d@2x.png",
            "11d" to "https://openweathermap.org/img/wn/11d@2x.png",
            "13d" to "https://openweathermap.org/img/wn/13d@2x.png",
            "50d" to "https://openweathermap.org/img/wn/50d@2x.png",
            // Night Icons
            "01n" to "https://openweathermap.org/img/wn/01n@2x.png",
            "02n" to "https://openweathermap.org/img/wn/02n@2x.png",
            "03n" to "https://openweathermap.org/img/wn/03n@2x.png",
            "04n" to "https://openweathermap.org/img/wn/04n@2x.png",
            "09n" to "https://openweathermap.org/img/wn/09n@2x.png",
            "10n" to "https://openweathermap.org/img/wn/10n@2x.png",
            "11n" to "https://openweathermap.org/img/wn/11n@2x.png",
            "13n" to "https://openweathermap.org/img/wn/13n@2x.png",
            "50n" to "https://openweathermap.org/img/wn/50n@2x.png"
        )

        @JvmStatic
        fun getIconUrl(iconName: String): String? {
            return ICON_URLS[iconName]
        }

        @JvmStatic
        fun getTemperatureFormat(feels_like: String, temp_min: String, temp_max: String): String? {
            return "${temp_max.toFloat().roundToInt()}°/${temp_min.toFloat().roundToInt()}° Feels like ${feels_like.toFloat().roundToInt()}°"
        }

        @JvmStatic
        fun getMainTemperatureFormat(temp: String): String {
            return "${temp.toFloat().roundToInt()}°"
        }

        @JvmStatic
        fun getTimestampShowableFormat(timestamp: String): String? {
            val elementDateTime = LocalDateTime.parse(
                timestamp,
                DateTimeFormatter.ofPattern("y-M-d HH:mm:ss")
            )
            return "${elementDateTime.dayOfWeek} at ${elementDateTime.hour}:00 to ${elementDateTime.hour + 3}:00"
        }
    }
}