package com.leoilagan.guidomia.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cars")
data class CarInfo(
    val consList: List<String>,
    val customerPrice: Double,
    val make: String,
    val marketPrice: Double,
    val model: String,
    val prosList: List<String>,
    val rating: Int
) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}