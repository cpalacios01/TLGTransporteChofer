/**
 * Activity de Login
 * Path: app/src/main/java/com/tlg/transporte/chofer/ui/login/LoginActivity.kt
 * Última modificación: 2026-02-05 15:00
 * Dev: cpalacios01@gmail.com | +595994648273
 */

package com.tlg.transporte.chofer.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.tlg.transporte.chofer.App
import com.tlg.transporte.chofer.databinding.ActivityLoginBinding
import com.tlg.transporte.chofer.ui.dashboard.DashboardActivity
import com.tlg.transporte.chofer.utils.Resource
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels { 
        (application as App).viewModelFactory 
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupViews()
        observeLoginState()
    }
    
    private fun setupViews() {
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            
            if (validateInputs(email, password)) {
                viewModel.login(email, password)
            }
        }
        
        binding.btnTogglePassword.setOnClickListener {
            togglePasswordVisibility()
        }
    }
    
    private fun validateInputs(email: String, password: String): Boolean {
        if (email.isEmpty()) {
            binding.tilEmail.error = "Ingrese su email"
            return false
        }
        if (password.isEmpty()) {
            binding.tilPassword.error = "Ingrese su contraseña"
            return false
        }
        binding.tilEmail.error = null
        binding.tilPassword.error = null
        return true
    }
    
    private var passwordVisible = false
    
    private fun togglePasswordVisibility() {
        passwordVisible = !passwordVisible
        binding.etPassword.inputType = if (passwordVisible) {
            android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        } else {
            android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
        binding.etPassword.setSelection(binding.etPassword.text?.length ?: 0)
    }
    
    private fun observeLoginState() {
        viewModel.loginState.observe(this) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Success -> {
                    showLoading(false)
                    resource.data?.let { user ->
                        lifecycleScope.launch {
                            (application as App).preferencesManager.saveLoginData(
                                user.id,
                                user.token ?: ""
                            )
                            
                            val intent = Intent(this@LoginActivity, DashboardActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
                is Resource.Error -> {
                    showLoading(false)
                    Toast.makeText(this, resource.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    
    private fun showLoading(show: Boolean) {
        binding.progressBar.visibility = if (show) View.VISIBLE else View.GONE
        binding.btnLogin.isEnabled = !show
    }
}