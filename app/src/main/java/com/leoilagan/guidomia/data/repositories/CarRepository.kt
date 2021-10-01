package com.leoilagan.guidomia.data.repositories

import com.leoilagan.guidomia.data.local.CarInfo

interface CarRepository {
     fun getJsonData() : List<CarInfo>

     suspend fun storedLocally(list : List<CarInfo>)

     suspend fun getDataFromDB() : List<CarInfo>

     suspend fun queryModel(model : String) : List<CarInfo>

     suspend fun queryMaker(maker : String) : List<CarInfo>

}