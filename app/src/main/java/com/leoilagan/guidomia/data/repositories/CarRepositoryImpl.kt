package com.leoilagan.guidomia.data.repositories

import android.content.Context
import com.google.gson.Gson
import com.leoilagan.guidomia.data.db.AppDatabase
import com.leoilagan.guidomia.data.db.dao.CarInfoDao
import com.leoilagan.guidomia.data.local.CarInfo
import java.io.IOException
import javax.inject.Inject

class CarRepositoryImpl @Inject constructor(
    private val context  : Context,
    appDatabase : AppDatabase

) : CarRepository{

    private var carInfoDao : CarInfoDao =  appDatabase.carInfoDao

    private   fun getJsonFileFromAssets() :String {
        val jsonString: String
        try {
            jsonString = context.assets.open("car_list.json")
                .bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return ""
        }
        return jsonString
    }

    override fun getJsonData(): List<CarInfo> {
        val jsonStr: String = getJsonFileFromAssets()
        val gson = Gson()
     return gson.fromJson(jsonStr, Array<CarInfo>::class.java).toList()

    }

    override suspend fun storedLocally(list : List<CarInfo>) {
        val result = carInfoDao.insertAll(*list.toTypedArray())
        var i = 0
        while (i < list.size) {
            list[i].uuid = result[i].toInt()
            ++i
        }
    }

    override suspend fun getDataFromDB(): List<CarInfo> {
      return carInfoDao.getAllCarInfo()
    }

    override suspend fun queryModel(model: String): List<CarInfo> {
        return carInfoDao.queryModel(model)
    }

    override suspend fun queryMaker(maker: String): List<CarInfo> {
        return carInfoDao.queryMaker(maker)
    }


}