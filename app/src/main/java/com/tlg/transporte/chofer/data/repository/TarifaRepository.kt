/**
 * Repositorio de tarifas
 * Path: app/src/main/java/com/tlg/transporte/chofer/data/repository/TarifaRepository.kt
 * Última modificación: 2026-02-05 14:45
 * Dev: cpalacios01@gmail.com | +595994648273
 */

package com.tlg.transporte.chofer.data.repository

import com.tlg.transporte.chofer.data.local.dao.TarifaDao
import com.tlg.transporte.chofer.data.local.entities.TarifaEntity
import com.tlg.transporte.chofer.data.remote.api.ApiService
import com.tlg.transporte.chofer.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TarifaRepository(
    private val apiService: ApiService,
    private val tarifaDao: TarifaDao
) {
    fun getTarifasActivas(): Flow<List<TarifaEntity>> = tarifaDao.getTarifasActivas()
    
    suspend fun syncTarifas(): Flow<Resource<List<TarifaEntity>>> = flow {
        emit(Resource.Loading())
        
        try {
            val response = apiService.getTarifas()
            
            if (response.isSuccessful && response.body() != null) {
                val tarifas = response.body()!!.map { dto ->
                    TarifaEntity(
                        id = dto.id,
                        nombre = dto.nombre,
                        monto = dto.monto,
                        distanciaKm = dto.distanciaKm,
                        descripcion = dto.descripcion,
                        activo = dto.activo,
                        createdAt = dto.createdAt,
                        updatedAt = dto.updatedAt
                    )
                }
                
                // Guardar en base de datos local
                tarifaDao.deleteAll()
                tarifaDao.insertAll(tarifas)
                
                emit(Resource.Success(tarifas))
            } else {
                // Si falla, devolver las locales
                val localTarifas = tarifaDao.getAllTarifas()
                emit(Resource.Error("Error al sincronizar, usando datos locales", localTarifas))
            }
        } catch (e: Exception) {
            // Si hay error de red, usar datos locales
            val localTarifas = tarifaDao.getAllTarifas()
            emit(Resource.Error(e.localizedMessage ?: "Sin conexión, usando datos locales", localTarifas))
        }
    }
}