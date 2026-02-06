/**
 * Repositorio de redondos
 * Path: app/src/main/java/com/tlg/transporte/chofer/data/repository/RedondoRepository.kt
 * Última modificación: 2026-02-05 14:45
 * Dev: cpalacios01@gmail.com | +595994648273
 */

package com.tlg.transporte.chofer.data.repository

import com.tlg.transporte.chofer.data.local.dao.RedondoDao
import com.tlg.transporte.chofer.data.local.entities.RedondoEntity
import com.tlg.transporte.chofer.data.remote.api.ApiService
import com.tlg.transporte.chofer.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RedondoRepository(
    private val apiService: ApiService,
    private val redondoDao: RedondoDao
) {
    fun getRedondoActivo(choferId: Int): Flow<RedondoEntity?> = 
        redondoDao.getRedondoActivo(choferId)
    
    suspend fun syncRedondoActivo(choferId: Int): Flow<Resource<RedondoEntity?>> = flow {
        emit(Resource.Loading())
        
        try {
            val response = apiService.getRedondoActivo()
            
            if (response.isSuccessful) {
                val dto = response.body()
                
                if (dto != null) {
                    val redondo = RedondoEntity(
                        serverId = dto.id,
                        codigoUnico = dto.codigoUnico,
                        fechaSalida = dto.fechaSalida,
                        choferId = dto.choferId,
                        busId = dto.busId,
                        itinerarioId = dto.itinerarioId,
                        montoInicial = dto.montoInicial,
                        fechaRegreso = dto.fechaRegreso,
                        estado = dto.estado,
                        sincronizado = true,
                        createdAt = null,
                        updatedAt = null
                    )
                    
                    redondoDao.insert(redondo)
                    emit(Resource.Success(redondo))
                } else {
                    emit(Resource.Success(null))
                }
            } else {
                emit(Resource.Error("Error al obtener redondo"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Error de conexión"))
        }
    }
}