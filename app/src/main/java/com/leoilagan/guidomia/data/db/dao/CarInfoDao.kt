package com.leoilagan.guidomia.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.leoilagan.guidomia.data.local.CarInfo


@Dao
interface CarInfoDao {

    @Insert
    suspend fun insertAll(vararg  carInfoDB : CarInfo) : List<Long>

    @Query("SELECT * from cars")
    suspend fun getAllCarInfo() : List<CarInfo>

    @Query("SELECT * FROM cars WHERE model LIKE '%' || :search || '%'")
    suspend fun queryModel(search : String) : List<CarInfo>

    @Query("SELECT * FROM cars WHERE make LIKE '%' || :search || '%'")
    suspend fun queryMaker(search : String) : List<CarInfo>
}