/**
 * DAO para entidad Redondo
 * Path: app/src/main/java/com/tlg/transporte/chofer/data/local/dao/RedondoDao.kt
 * Última modificación: 2026-02-05 14:35
 * Dev: cpalacios01@gmail.com | +595994648273
 */

package com.tlg.transporte.chofer.data.local.dao

import androidx.room.*
import com.tlg.transporte.chofer.data.local.entities.RedondoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RedondoDao {
    @Query("SELECT * FROM redondos WHERE estado = 'abierto' AND choferId = :choferId LIMIT 1")
    fun getRedondoActivo(choferId: Int): Flow<RedondoEntity?>

    @Query("SELECT * FROM redondos WHERE sincronizado = 0")
    suspend fun getRedondosNoSincronizados(): List<RedondoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(redondo: RedondoEntity): Long

    @Update
    suspend fun update(redondo: RedondoEntity)

    @Query("UPDATE redondos SET sincronizado = 1, serverId = :serverId WHERE localId = :localId")
    suspend fun markAsSynchronized(localId: Long, serverId: Int)
}