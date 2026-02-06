/**
 * Entidad de tarifa para Room Database
 * Path: app/src/main/java/com/tlg/transporte/chofer/data/local/entities/TarifaEntity.kt
 * Última modificación: 2026-02-05 14:30
 * Dev: cpalacios01@gmail.com | +595994648273
 */

package com.tlg.transporte.chofer.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tarifas")
data class TarifaEntity(
    @PrimaryKey
    val id: Int,
    val nombre: String,
    val monto: Double,
    val distanciaKm: Double?,
    val descripcion: String?,
    val activo: Boolean,
    val createdAt: String?,
    val updatedAt: String?
)