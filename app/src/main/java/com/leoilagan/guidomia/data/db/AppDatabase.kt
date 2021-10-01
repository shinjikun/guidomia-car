package com.leoilagan.guidomia.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.leoilagan.guidomia.data.db.dao.CarInfoDao
import com.leoilagan.guidomia.data.db.model.Converters
import com.leoilagan.guidomia.data.local.CarInfo

@Database (
    entities = [CarInfo::class], version = 1, exportSchema = false
        )
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract  val carInfoDao : CarInfoDao

    companion object {

        private var INSTANCE : AppDatabase? = null

        /**
         * getting the database instance, this is common to whole app
         */
        fun getInstance(context: Context) : AppDatabase {
            var instance = INSTANCE

            if(instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "cars.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
            }
            return instance
        }

    }
}