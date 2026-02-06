/**
 * ViewModel para Login
 * Path: app/src/main/java/com/tlg/transporte/chofer/ui/login/LoginViewModel.kt
 * Última modificación: 2026-02-05 14:55
 * Dev: cpalacios01@gmail.com | +595994648273
 */

package com.tlg.transporte.chofer.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tlg.transporte.chofer.data.local.entities.UserEntity
import com.tlg.transporte.chofer.data.repository.AuthRepository
import com.tlg.transporte.chofer.utils.Resource
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
    
    private val _loginState = MutableLiveData<Resource<UserEntity>>()
    val loginState: LiveData<Resource<UserEntity>> = _loginState
    
    fun login(email: String, password: String) {
        viewModelScope.launch {
            authRepository.login(email, password).collect { resource ->
                _loginState.value = resource
            }
        }
    }
}