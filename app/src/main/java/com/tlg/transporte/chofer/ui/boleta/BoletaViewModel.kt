/**
 * ViewModel para Boletas
 * Path: app/src/main/java/com/tlg/transporte/chofer/ui/boleta/BoletaViewModel.kt
 * Última modificación: 2026-02-05 14:55
 * Dev: cpalacios01@gmail.com | +595994648273
 */

package com.tlg.transporte.chofer.ui.boleta

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tlg.transporte.chofer.data.local.entities.BoletaEntity
import com.tlg.transporte.chofer.data.repository.BoletaRepository
import com.tlg.transporte.chofer.utils.Resource
import kotlinx.coroutines.launch

class BoletaViewModel(
    private val boletaRepository: BoletaRepository
) : ViewModel() {
    
    private val _boletas = MutableLiveData<List<BoletaEntity>>()
    val boletas: LiveData<List<BoletaEntity>> = _boletas
    
    private val _createBoletaState = MutableLiveData<Resource<BoletaEntity>>()
    val createBoletaState: LiveData<Resource<BoletaEntity>> = _createBoletaState
    
    fun loadBoletas(redondoLocalId: Long) {
        viewModelScope.launch {
            boletaRepository.getBoletasByRedondo(redondoLocalId).collect { boletas ->
                _boletas.value = boletas
            }
        }
    }
    
    fun createBoleta(
        redondoLocalId: Long,
        redondoServerId: Int?,
        tarifaId: Int,
        monto: Double
    ) {
        viewModelScope.launch {
            boletaRepository.createBoleta(
                redondoLocalId,
                redondoServerId,
                tarifaId,
                monto
            ).collect { resource ->
                _createBoletaState.value = resource
            }
        }
    }
}