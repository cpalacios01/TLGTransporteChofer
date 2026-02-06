/**
 * Activity principal Dashboard
 * Path: app/src/main/java/com/tlg/transporte/chofer/ui/dashboard/DashboardActivity.kt
 * Última modificación: 2026-02-05 15:00
 * Dev: cpalacios01@gmail.com | +595994648273
 */

package com.tlg.transporte.chofer.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.tlg.transporte.chofer.App
import com.tlg.transporte.chofer.databinding.ActivityDashboardBinding
import com.tlg.transporte.chofer.ui.boleta.BoletaActivity
import com.tlg.transporte.chofer.ui.login.LoginActivity
import com.tlg.transporte.chofer.utils.NetworkUtils
import com.tlg.transporte.chofer.utils.Resource

class DashboardActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityDashboardBinding
    private val viewModel: DashboardViewModel by viewModels { 
        (application as App).viewModelFactory 
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupViews()
        observeData()
    }
    
    private fun setupViews() {
        binding.btnEmitirBoleta.setOnClickListener {
            viewModel.redondoActivo.value?.let { redondo ->
                val intent = Intent(this, BoletaActivity::class.java)
                intent.putExtra("redondo_local_id", redondo.localId)
                intent.putExtra("redondo_server_id", redondo.serverId)
                startActivity(intent)
            } ?: run {
                Toast.makeText(this, "No hay redondo activo", Toast.LENGTH_SHORT).show()
            }
        }
        
        binding.btnSync.setOnClickListener {
            if (NetworkUtils.isNetworkAvailable(this)) {
                viewModel.syncData()
            } else {
                Toast.makeText(this, "Sin conexión a internet", Toast.LENGTH_SHORT).show()
            }
        }
        
        binding.btnLogout.setOnClickListener {
            showLogoutDialog()
        }
        
        binding.swipeRefresh.setOnRefreshListener {
            if (NetworkUtils.isNetworkAvailable(this)) {
                viewModel.syncData()
            } else {
                binding.swipeRefresh.isRefreshing = false
                Toast.makeText(this, "Sin conexión a internet", Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    private fun observeData() {
        viewModel.currentUser.observe(this) { user ->
            user?.let {
                binding.tvUserName.text = "Chofer: ${it.nombre}"
            }
        }
        
        viewModel.redondoActivo.observe(this) { redondo ->
            if (redondo != null) {
                binding.cardRedondo.visibility = View.VISIBLE
                binding.tvNoRedondo.visibility = View.GONE
                binding.tvCodigoRedondo.text = "Código: ${redondo.codigoUnico ?: "Pendiente"}"
                binding.tvEstadoRedondo.text = "Estado: ${redondo.estado}"
                binding.btnEmitirBoleta.isEnabled = true
            } else {
                binding.cardRedondo.visibility = View.GONE
                binding.tvNoRedondo.visibility = View.VISIBLE
                binding.btnEmitirBoleta.isEnabled = false
            }
        }
        
        viewModel.syncState.observe(this) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.swipeRefresh.isRefreshing = false
                    Toast.makeText(this, resource.data, Toast.LENGTH_SHORT).show()
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.swipeRefresh.isRefreshing = false
                    Toast.makeText(this, resource.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    
    private fun showLogoutDialog() {
        AlertDialog.Builder(this)
            .setTitle("Cerrar Sesión")
            .setMessage("¿Está seguro que desea cerrar sesión?")
            .setPositiveButton("Sí") { _, _ ->
                viewModel.logout()
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
            .setNegativeButton("No", null)
            .show()
    }
}