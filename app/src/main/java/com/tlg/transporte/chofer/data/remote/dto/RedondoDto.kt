/**
 * DTO para Redondo
 * Path: app/src/main/java/com/tlg/transporte/chofer/data/remote/dto/RedondoDto.kt
 * Última modificación: 2026-02-05 14:40
 * Dev: cpalacios01@gmail.com | +595994648273
 */

package com.tlg.transporte.chofer.data.remote.dto

import com.google.gson.annotations.SerializedName

data class RedondoDto(
    val id: Int,
    @SerializedName("codigo_unico")
    val codigoUnico: String,
    @SerializedName("fecha_salida")
    val fechaSalida: String,
    @SerializedName("chofer_id")
    val choferId: Int,
    @SerializedName("bus_id")
    val busId: Int,
    @SerializedName("itinerario_id")
    val itinerarioId: Int,
    @SerializedName("monto_inicial")
    val montoInicial: Double,
    @SerializedName("fecha_regreso")
    val fechaRegreso: String?,
    val estado: String,
    @SerializedName("total_boletas")
    val totalBoletas: Int?,
    @SerializedName("total_recaudado")
    val totalRecaudado: Double?,
    @SerializedName("total_gastos")
    val totalGastos: Double?,
    @SerializedName("debe_entregar")
    val debeEntregar: Double?,
    val bus: BusDto?,
    val itinerario: ItinerarioDto?
)

data class BusDto(
    val id: Int,
    val numero: String,
    val patente: String,
    val marca: String?,
    val modelo: String?
)

data class ItinerarioDto(
    val id: Int,
    val nombre: String,
    val descripcion: String?,
    val paradas: List<String>
)