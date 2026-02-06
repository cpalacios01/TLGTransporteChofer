/**
 * DAO para entidad Gasto
 * Path: app/src/main/java/com/tlg/transporte/chofer/data/local/dao/GastoDao.kt
 * Última modificación: 2026-02-05 14:35
 * Dev: cpalacios01@gmail.com | +595994648273
 */

package com.tlg.transporte.chofer.data.local.dao

import androidx.room.*
import com.tlg.transporte.chofer.data.local.entities.GastoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GastoDao {
    @Query("SELECT * FROM gastos WHERE redondoLocalId = :redondoLocalId ORDER BY fechaHora DESC")
    fun getGastosByRedondo(redondoLocalId: Long): Flow<List<GastoEntity>>

    @Query("SELECT SUM(monto) FROM gastos WHERE redondoLocalId = :redondoLocalId")
    suspend fun getTotalGastos(redondoLocalId: Long): Double?

    @Query("SELECT * FROM gastos WHERE sincronizado = 0")
    suspend fun getGastosNoSincronizados(): List<GastoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(gasto: GastoEntity): Long

    @Query("UPDATE gastos SET sincronizado = 1, serverId = :serverId WHERE localId = :localId")
    suspend fun markAsSynchronized(localId: Long, serverId: Int)
}