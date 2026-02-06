/**
 * Clase Application principal
 * Path: app/src/main/java/com/tlg/transporte/chofer/App.kt
 * Última modificación: 2026-02-05 15:00
 * Dev: cpalacios01@gmail.com | +595994648273
 */

package com.tlg.transporte.chofer

import android.app.Application
import com.tlg.transporte.chofer.data.local.AppDatabase
import com.tlg.transporte.chofer.data.remote.api.RetrofitClient
import com.tlg.transporte.chofer.data.repository.*
import com.tlg.transporte.chofer.ui.ViewModelFactory
import com.tlg.transporte.chofer.utils.PreferencesManager

class App : Application() {
    
    private val database by lazy { AppDatabase.getDatabase(this) }
    private val apiService by lazy { RetrofitClient.apiService }
    
    // Repositories
    val authRepository by lazy { 
        AuthRepository(apiService, database.userDao()) 
    }
    
    val tarifaRepository by lazy { 
        TarifaRepository(apiService, database.tarifaDao()) 
    }
    
    val redondoRepository by lazy { 
        RedondoRepository(apiService, database.redondoDao()) 
    }
    
    val boletaRepository by lazy { 
        BoletaRepository(apiService, database.boletaDao()) 
    }
    
    // ViewModelFactory
    val viewModelFactory by lazy {
        ViewModelFactory(
            authRepository,
            tarifaRepository,
            redondoRepository,
            boletaRepository
        )
    }
    
    val preferencesManager by lazy { PreferencesManager(this) }
    
    override fun onCreate() {
        super.onCreate()
    }
}