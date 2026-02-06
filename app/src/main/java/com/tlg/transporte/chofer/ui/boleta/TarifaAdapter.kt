/**
 * Adapter para lista de tarifas
 * Path: app/src/main/java/com/tlg/transporte/chofer/ui/boleta/TarifaAdapter.kt
 * Última modificación: 2026-02-05 15:05
 * Dev: cpalacios01@gmail.com | +595994648273
 */

package com.tlg.transporte.chofer.ui.boleta

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tlg.transporte.chofer.data.local.entities.TarifaEntity
import com.tlg.transporte.chofer.databinding.ItemTarifaBinding
import java.text.NumberFormat
import java.util.*

class TarifaAdapter(
    private val onTarifaClick: (TarifaEntity) -> Unit
) : ListAdapter<TarifaEntity, TarifaAdapter.TarifaViewHolder>(TarifaDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarifaViewHolder {
        val binding = ItemTarifaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TarifaViewHolder(binding, onTarifaClick)
    }
    
    override fun onBindViewHolder(holder: TarifaViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    class TarifaViewHolder(
        private val binding: ItemTarifaBinding,
        private val onTarifaClick: (TarifaEntity) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(tarifa: TarifaEntity) {
            val numberFormat = NumberFormat.getCurrencyInstance(Locale("es", "PY"))
            
            binding.tvNombreTarifa.text = tarifa.nombre
            binding.tvMontoTarifa.text = numberFormat.format(tarifa.monto)
            tarifa.descripcion?.let {
                binding.tvDescripcionTarifa.text = it
            }
            
            binding.root.setOnClickListener {
                onTarifaClick(tarifa)
            }
        }
    }
    
    class TarifaDiffCallback : DiffUtil.ItemCallback<TarifaEntity>() {
        override fun areItemsTheSame(oldItem: TarifaEntity, newItem: TarifaEntity): Boolean {
            return oldItem.id == newItem.id
        }
        
        override fun areContentsTheSame(oldItem: TarifaEntity, newItem: TarifaEntity): Boolean {
            return oldItem == newItem
        }
    }
}