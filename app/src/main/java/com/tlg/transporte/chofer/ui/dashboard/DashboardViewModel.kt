/**
 * ViewModel para Dashboard
 * Path: app/src/main/java/com/tlg/transporte/chofer/ui/dashboard/DashboardViewModel.kt
 * Última modificación: 2026-02-05 14:55
 * Dev: cpalacios01@gmail.com | +595994648273
 */

package com.tlg.transporte.chofer.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tlg.transporte.chofer.data.local.entities.RedondoEntity
import com.tlg.transporte.chofer.data.local.entities.TarifaEntity
import com.tlg.transporte.chofer.data.local.entities.UserEntity
import com.tlg.transporte.chofer.data.repository.AuthRepository
import com.tlg.transporte.chofer.data.repository.RedondoRepository
import com.tlg.transporte.chofer.data.repository.TarifaRepository
import com.tlg.transporte.chofer.utils.Resource
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val authRepository: AuthRepository,
    private val tarifaRepository: TarifaRepository,
    private val redondoRepository: RedondoRepository
) : ViewModel() {
    
    private val _currentUser = MutableLiveData<UserEntity?>()
    val currentUser: LiveData<UserEntity?> = _currentUser
    
    private val _redondoActivo = MutableLiveData<RedondoEntity?>()
    val redondoActivo: LiveData<RedondoEntity?> = _redondoActivo
    
    private val _tarifas = MutableLiveData<List<TarifaEntity>>()
    val tarifas: LiveData<List<TarifaEntity>> = _tarifas
    
    private val _syncState = MutableLiveData<Resource<String>>()
    val syncState: LiveData<Resource<String>> = _syncState
    
    init {
        loadUserData()
    }
    
    private fun loadUserData() {
        viewModelScope.launch {
            authRepository.getCurrentUser().collect { user ->
                _currentUser.value = user
                user?.let {
                    loadRedondoActivo(it.id)
                    loadTarifas()
                }
            }
        }
    }
    
    private fun loadRedondoActivo(choferId: Int) {
        viewModelScope.launch {
            redondoRepository.getRedondoActivo(choferId).collect { redondo ->
                _redondoActivo.value = redondo
            }
        }
    }
    
    private fun loadTarifas() {
        viewModelScope.launch {
            tarifaRepository.getTarifasActivas().collect { tarifas ->
                _tarifas.value = tarifas
            }
        }
    }
    
    fun syncData() {
        viewModelScope.launch {
            _syncState.value = Resource.Loading()
            
            // Sincronizar tarifas
            tarifaRepository.syncTarifas().firstOrNull()
            
            // Sincronizar redondo activo
            _currentUser.value?.let { user ->
                redondoRepository.syncRedondoActivo(user.id).firstOrNull()
            }
            
            _syncState.value = Resource.Success("Sincronización completada")
        }
    }
    
    fun logout() {
        viewModelScope.launch {
            authRepository.logout()
        }
    }
}