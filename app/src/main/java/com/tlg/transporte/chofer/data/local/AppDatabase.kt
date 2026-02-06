/**
 * Base de datos principal de la aplicación
 * Path: app/src/main/java/com/tlg/transporte/chofer/data/local/AppDatabase.kt
 * Última modificación: 2026-02-05 14:35
 * Dev: cpalacios01@gmail.com | +595994648273
 */

package com.tlg.transporte.chofer.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tlg.transporte.chofer.data.local.dao.*
import com.tlg.transporte.chofer.data.local.entities.*

@Database(
    entities = [
        UserEntity::class,
        TarifaEntity::class,
        RedondoEntity::class,
        BoletaEntity::class,
        GastoEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun tarifaDao(): TarifaDao
    abstract fun redondoDao(): RedondoDao
    abstract fun boletaDao(): BoletaDao
    abstract fun gastoDao(): GastoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "tlg_transporte_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}