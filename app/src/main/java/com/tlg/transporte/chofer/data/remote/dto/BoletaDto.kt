/**
 * DTO para Boleta
 * Path: app/src/main/java/com/tlg/transporte/chofer/data/remote/dto/BoletaDto.kt
 * Última modificación: 2026-02-05 14:40
 * Dev: cpalacios01@gmail.com | +595994648273
 */

package com.tlg.transporte.chofer.data.remote.dto

import com.google.gson.annotations.SerializedName

data class BoletaDto(
    val id: Int?,
    @SerializedName("redondo_id")
    val redondoId: Int,
    @SerializedName("tarifa_id")
    val tarifaId: Int,
    @SerializedName("numero_boleta")
    val numeroBoleta: String,
    val monto: Double,
    @SerializedName("fecha_hora")
    val fechaHora: String,
    @SerializedName("created_offline")
    val createdOffline: Boolean = true
)