/**
 * Repositorio de boletas
 * Path: app/src/main/java/com/tlg/transporte/chofer/data/repository/BoletaRepository.kt
 * Última modificación: 2026-02-05 14:45
 * Dev: cpalacios01@gmail.com | +595994648273
 */

package com.tlg.transporte.chofer.data.repository

import com.tlg.transporte.chofer.data.local.dao.BoletaDao
import com.tlg.transporte.chofer.data.local.entities.BoletaEntity
import com.tlg.transporte.chofer.data.remote.api.ApiService
import com.tlg.transporte.chofer.data.remote.dto.BoletaDto
//import com.tlg.transporte.chofer.data.remote.dto.BoletasLoteRequest
// * Path: app/src/main/java/com/tlg/transporte/chofer/data/remote/api/ApiService.kt
import com.tlg.transporte.chofer.data.remote.api.BoletasLoteRequest
import com.tlg.transporte.chofer.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.text.SimpleDateFormat
import java.util.*

class BoletaRepository(
    private val apiService: ApiService,
    private val boletaDao: BoletaDao
) {
    fun getBoletasByRedondo(redondoLocalId: Long): Flow<List<BoletaEntity>> =
        boletaDao.getBoletasByRedondo(redondoLocalId)
    
    suspend fun createBoleta(
        redondoLocalId: Long,
        redondoServerId: Int?,
        tarifaId: Int,
        monto: Double
    ): Flow<Resource<BoletaEntity>> = flow {
        emit(Resource.Loading())
        
        try {
            val timestamp = SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(Date())
            val random = (1000..9999).random()
            val numeroBoleta = "BOL-$timestamp-$random"
            val fechaHora = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
            
            val boleta = BoletaEntity(
                redondoLocalId = redondoLocalId,
                redondoServerId = redondoServerId,
                tarifaId = tarifaId,
                numeroBoleta = numeroBoleta,
                monto = monto,
                fechaHora = fechaHora,
                createdOffline = true,
                sincronizado = false,
                serverId = null,
                createdAt = fechaHora,
                updatedAt = fechaHora
            )
            
            val localId = boletaDao.insert(boleta)
            val insertedBoleta = boleta.copy(localId = localId)
            
            emit(Resource.Success(insertedBoleta))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Error al crear boleta"))
        }
    }
    
    suspend fun syncBoletas(): Flow<Resource<Int>> = flow {
        emit(Resource.Loading())
        
        try {
            val boletasNoSincronizadas = boletaDao.getBoletasNoSincronizadas()
            
            if (boletasNoSincronizadas.isEmpty()) {
                emit(Resource.Success(0))
                return@flow
            }
            
            val boletasDto = boletasNoSincronizadas.map { entity ->
                BoletaDto(
                    id = entity.serverId,
                    redondoId = entity.redondoServerId ?: 0,
                    tarifaId = entity.tarifaId,
                    numeroBoleta = entity.numeroBoleta,
                    monto = entity.monto,
                    fechaHora = entity.fechaHora,
                    createdOffline = entity.createdOffline
                )
            }
            
            val response = apiService.createBoletasLote(BoletasLoteRequest(boletasDto))
            
            if (response.isSuccessful && response.body() != null) {
                val result = response.body()!!
                
                // Marcar como sincronizadas
                result.boletas.forEachIndexed { index, dto ->
                    if (dto.id != null) {
                        boletaDao.markAsSynchronized(
                            boletasNoSincronizadas[index].localId,
                            dto.id
                        )
                    }
                }
                
                emit(Resource.Success(result.total))
            } else {
                emit(Resource.Error("Error al sincronizar boletas"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Error de conexión"))
        }
    }
}