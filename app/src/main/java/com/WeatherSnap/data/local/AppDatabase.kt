package com.weathersnap.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.weathersnap.data.local.dao.ReportDao
import com.weathersnap.data.local.entity.ReportEntity

@Database(entities = [ReportEntity::class], version = 1)
abstract class AppDatabase :
    RoomDatabase() {
    abstract fun reportDao():
            ReportDao
}