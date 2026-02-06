/**
 * Splash Screen Activity
 * Path: app/src/main/java/com/tlg/transporte/chofer/ui/splash/SplashActivity.kt
 * Última modificación: 2026-02-05 15:00
 * Dev: cpalacios01@gmail.com | +595994648273
 */

package com.tlg.transporte.chofer.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.tlg.transporte.chofer.App
import com.tlg.transporte.chofer.databinding.ActivitySplashBinding
import com.tlg.transporte.chofer.ui.dashboard.DashboardActivity
import com.tlg.transporte.chofer.ui.login.LoginActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivitySplashBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        lifecycleScope.launch {
            delay(2000) // 2 segundos
            
            val app = application as App
            val isLoggedIn = app.preferencesManager.isLoggedIn.first()
            
            val intent = if (isLoggedIn) {
                Intent(this@SplashActivity, DashboardActivity::class.java)
            } else {
                Intent(this@SplashActivity, LoginActivity::class.java)
            }
            
            startActivity(intent)
            finish()
        }
    }
}