/**
 * DAO para entidad Boleta
 * Path: app/src/main/java/com/tlg/transporte/chofer/data/local/dao/BoletaDao.kt
 * Última modificación: 2026-02-05 14:35
 * Dev: cpalacios01@gmail.com | +595994648273
 */

package com.tlg.transporte.chofer.data.local.dao

import androidx.room.*
import com.tlg.transporte.chofer.data.local.entities.BoletaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BoletaDao {
    @Query("SELECT * FROM boletas WHERE redondoLocalId = :redondoLocalId ORDER BY fechaHora DESC")
    fun getBoletasByRedondo(redondoLocalId: Long): Flow<List<BoletaEntity>>

    @Query("SELECT COUNT(*) FROM boletas WHERE redondoLocalId = :redondoLocalId")
    suspend fun countBoletasByRedondo(redondoLocalId: Long): Int

    @Query("SELECT SUM(monto) FROM boletas WHERE redondoLocalId = :redondoLocalId")
    suspend fun getTotalRecaudado(redondoLocalId: Long): Double?

    @Query("SELECT * FROM boletas WHERE sincronizado = 0")
    suspend fun getBoletasNoSincronizadas(): List<BoletaEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(boleta: BoletaEntity): Long

    @Query("UPDATE boletas SET sincronizado = 1, serverId = :serverId WHERE localId = :localId")
    suspend fun markAsSynchronized(localId: Long, serverId: Int)
}