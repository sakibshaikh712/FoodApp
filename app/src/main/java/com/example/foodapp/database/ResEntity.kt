package com.example.foodapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurants")
data class ResEntity (
    @PrimaryKey val res_id:Int,
    @ColumnInfo(name = "res_name") val restaurantName: String,
    @ColumnInfo(name = "res_rating") val restaurantRating: String,
    @ColumnInfo(name = "res_price") val restaurantPrice: String,
    @ColumnInfo(name = "res_image") val restaurantImage: String
    )