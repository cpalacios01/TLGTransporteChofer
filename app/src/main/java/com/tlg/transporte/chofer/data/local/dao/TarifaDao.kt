/**
 * DAO para entidad Tarifa
 * Path: app/src/main/java/com/tlg/transporte/chofer/data/local/dao/TarifaDao.kt
 * Última modificación: 2026-02-05 14:35
 * Dev: cpalacios01@gmail.com | +595994648273
 */

package com.tlg.transporte.chofer.data.local.dao

import androidx.room.*
import com.tlg.transporte.chofer.data.local.entities.TarifaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TarifaDao {
    @Query("SELECT * FROM tarifas WHERE activo = 1 ORDER BY monto ASC")
    fun getTarifasActivas(): Flow<List<TarifaEntity>>

    @Query("SELECT * FROM tarifas")
    suspend fun getAllTarifas(): List<TarifaEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tarifas: List<TarifaEntity>)

    @Query("DELETE FROM tarifas")
    suspend fun deleteAll()
}