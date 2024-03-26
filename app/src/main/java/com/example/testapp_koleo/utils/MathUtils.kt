package com.example.testapp_koleo.utils

import kotlin.math.asin
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

fun calculateDistance(
    firstLatitude: Double, firstLongitude: Double,
    secondLatitude: Double, secondLongitude: Double
): Double {

    val earthRadius = 6371.0

    val deltaLat = secondLatitude - firstLatitude
    val deltaLon = secondLongitude - firstLongitude

    val a = sin(deltaLat / 2).pow(2) +
            cos(firstLatitude) * cos(secondLatitude) *
            sin(deltaLon / 2).pow(2)
    val c = 2 * asin(sqrt(a))

    return earthRadius * c
}

fun Double.toRadians() = this * Math.PI / 180