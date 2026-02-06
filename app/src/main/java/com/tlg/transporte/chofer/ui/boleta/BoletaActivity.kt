/**
 * Activity de emisión de boletas
 * Path: app/src/main/java/com/tlg/transporte/chofer/ui/boleta/BoletaActivity.kt
 * Última modificación: 2026-02-05 15:05
 * Dev: cpalacios01@gmail.com | +595994648273
 */

package com.tlg.transporte.chofer.ui.boleta

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.tlg.transporte.chofer.App
import com.tlg.transporte.chofer.databinding.ActivityBoletaBinding
import com.tlg.transporte.chofer.ui.dashboard.DashboardViewModel
import com.tlg.transporte.chofer.utils.Resource

class BoletaActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityBoletaBinding
    private val dashboardViewModel: DashboardViewModel by viewModels { 
        (application as App).viewModelFactory 
    }
    private val boletaViewModel: BoletaViewModel by viewModels { 
        (application as App).viewModelFactory 
    }
    
    private lateinit var tarifaAdapter: TarifaAdapter
    private var redondoLocalId: Long = 0
    private var redondoServerId: Int? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoletaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        redondoLocalId = intent.getLongExtra("redondo_local_id", 0)
        redondoServerId = intent.getIntExtra("redondo_server_id", -1).takeIf { it != -1 }
        
        if (redondoLocalId == 0L) {
            Toast.makeText(this, "Error: ID de redondo inválido", Toast.LENGTH_SHORT).show()
            finish()
            return
        }
        
        setupViews()
        observeData()
        
        boletaViewModel.loadBoletas(redondoLocalId)
    }
    
    private fun setupViews() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Emitir Boleta"
        
        tarifaAdapter = TarifaAdapter { tarifa ->
            boletaViewModel.createBoleta(
                redondoLocalId,
                redondoServerId,
                tarifa.id,
                tarifa.monto
            )
        }
        
        binding.rvTarifas.apply {
            layoutManager = LinearLayoutManager(this@BoletaActivity)
            adapter = tarifaAdapter
        }
    }
    
    private fun observeData() {
        dashboardViewModel.tarifas.observe(this) { tarifas ->
            tarifaAdapter.submitList(tarifas)
        }
        
        boletaViewModel.createBoletaState.observe(this) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, "Boleta creada: ${resource.data?.numeroBoleta}", Toast.LENGTH_SHORT).show()
                    // TODO: Imprimir boleta
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, resource.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}