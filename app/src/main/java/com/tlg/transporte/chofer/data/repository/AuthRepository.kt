/**
 * Repositorio de autenticación
 * Path: app/src/main/java/com/tlg/transporte/chofer/data/repository/AuthRepository.kt
 * Última modificación: 2026-02-05 14:45
 * Dev: cpalacios01@gmail.com | +595994648273
 */

package com.tlg.transporte.chofer.data.repository

import com.tlg.transporte.chofer.data.local.dao.UserDao
import com.tlg.transporte.chofer.data.local.entities.UserEntity
import com.tlg.transporte.chofer.data.remote.api.ApiService
import com.tlg.transporte.chofer.data.remote.api.RetrofitClient
import com.tlg.transporte.chofer.data.remote.dto.LoginRequest
import com.tlg.transporte.chofer.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthRepository(
    private val apiService: ApiService,
    private val userDao: UserDao
) {
    suspend fun login(email: String, password: String): Flow<Resource<UserEntity>> = flow {
        emit(Resource.Loading())
        
        try {
            val response = apiService.login(LoginRequest(email, password))
            
            if (response.isSuccessful && response.body() != null) {
                val loginResponse = response.body()!!
                
                val userEntity = UserEntity(
                    id = loginResponse.user.id,
                    nombre = loginResponse.user.nombre,
                    email = loginResponse.user.email,
                    roleId = loginResponse.user.roleId,
                    roleName = loginResponse.user.roleName,
                    busId = loginResponse.user.busId,
                    token = loginResponse.token
                )
                
                // Guardar en base de datos local
                userDao.deleteAll()
                userDao.insertUser(userEntity)
                
                // Configurar token en Retrofit
                RetrofitClient.setToken(loginResponse.token)
                
                emit(Resource.Success(userEntity))
            } else {
                emit(Resource.Error("Credenciales incorrectas"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Error de conexión"))
        }
    }
    
    fun getCurrentUser(): Flow<UserEntity?> = userDao.getCurrentUser()
    
    suspend fun logout() {
        userDao.deleteAll()
        RetrofitClient.setToken(null)
    }
}