package org.marproject.lawancovid19.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.marproject.lawancovid19.utils.Constants.CORONA_DB

@Suppress("SpellCheckingInspection")
@Database(entities = [DataIndonesiaDB::class,
    DataProvinsiDB::class,
    DataGlobalDB::class],
    version = 1,
    exportSchema = false)
abstract class CoronaDatabase : RoomDatabase() {

    abstract val dataIndonesiaDao: DataIndonesiaDao
    abstract val dataProvinsiDao: DataProvinsiDao
    abstract val dataGlobalDao: DataGlobalDao

    companion object {
        @Volatile
        private var INSTANCE: CoronaDatabase? = null

        fun getInstance(context: Context): CoronaDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CoronaDatabase::class.java,
                        CORONA_DB
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}