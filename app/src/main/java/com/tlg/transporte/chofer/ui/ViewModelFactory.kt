/**
 * Factory para crear ViewModels
 * Path: app/src/main/java/com/tlg/transporte/chofer/ui/ViewModelFactory.kt
 * Última modificación: 2026-02-05 15:00
 * Dev: cpalacios01@gmail.com | +595994648273
 */

package com.tlg.transporte.chofer.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tlg.transporte.chofer.data.repository.*
import com.tlg.transporte.chofer.ui.boleta.BoletaViewModel
import com.tlg.transporte.chofer.ui.dashboard.DashboardViewModel
import com.tlg.transporte.chofer.ui.login.LoginViewModel

class ViewModelFactory(
    private val authRepository: AuthRepository,
    private val tarifaRepository: TarifaRepository,
    private val redondoRepository: RedondoRepository,
    private val boletaRepository: BoletaRepository
) : ViewModelProvider.Factory {
    
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(authRepository) as T
            }
            modelClass.isAssignableFrom(DashboardViewModel::class.java) -> {
                DashboardViewModel(authRepository, tarifaRepository, redondoRepository) as T
            }
            modelClass.isAssignableFrom(BoletaViewModel::class.java) -> {
                BoletaViewModel(boletaRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}