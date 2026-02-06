/**
 * DTO para Tarifa
 * Path: app/src/main/java/com/tlg/transporte/chofer/data/remote/dto/TarifaDto.kt
 * Última modificación: 2026-02-05 14:40
 * Dev: cpalacios01@gmail.com | +595994648273
 */

package com.tlg.transporte.chofer.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TarifaDto(
    val id: Int,
    val nombre: String,
    val monto: Double,
    @SerializedName("distancia_km")
    val distanciaKm: Double?,
    val descripcion: String?,
    val activo: Boolean,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("updated_at")
    val updatedAt: String?
)