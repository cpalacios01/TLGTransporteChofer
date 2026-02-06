/**
 * DTO para Gasto
 * Path: app/src/main/java/com/tlg/transporte/chofer/data/remote/dto/GastoDto.kt
 * Última modificación: 2026-02-05 14:40
 * Dev: cpalacios01@gmail.com | +595994648273
 */

package com.tlg.transporte.chofer.data.remote.dto

import com.google.gson.annotations.SerializedName

data class GastoDto(
    val id: Int?,
    @SerializedName("redondo_id")
    val redondoId: Int,
    val concepto: String,
    val monto: Double,
    val comprobante: String?,
    val descripcion: String?,
    @SerializedName("fecha_hora")
    val fechaHora: String
)