package org.legd.oriontekdemo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.legd.oriontekdemo.database.dao.DemoDao
import org.legd.oriontekdemo.database.model.Address
import org.legd.oriontekdemo.database.model.User

/**
 * Abstract class to describe the database.
 */
@Database(entities = arrayOf(User::class, Address::class), version = 1, exportSchema = false)
abstract class DemoDataBase : RoomDatabase() {

    abstract fun getUsersWithAddressesDao() : DemoDao

    companion object {

        @Volatile
        private var INSTANCE: DemoDataBase? = null

        fun getDatabase(context: Context): DemoDataBase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DemoDataBase::class.java,
                    "demo_database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}