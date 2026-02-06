/**
 * Interfaz de servicios API
 * Path: app/src/main/java/com/tlg/transporte/chofer/data/remote/api/ApiService.kt
 * Última modificación: 2026-02-05 14:40
 * Dev: cpalacios01@gmail.com | +595994648273
 */

package com.tlg.transporte.chofer.data.remote.api

import com.tlg.transporte.chofer.data.remote.dto.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @POST("login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @GET("tarifas")
    suspend fun getTarifas(): Response<List<TarifaDto>>

    @GET("redondos/activo")
    suspend fun getRedondoActivo(): Response<RedondoDto?>

    @POST("boletas")
    suspend fun createBoleta(@Body boleta: BoletaDto): Response<BoletaDto>

    @POST("boletas/lote")
    suspend fun createBoletasLote(@Body boletas: BoletasLoteRequest): Response<BoletasLoteResponse>

    @POST("gastos")
    suspend fun createGasto(@Body gasto: GastoDto): Response<GastoDto>

    @GET("ping")
    suspend fun ping(): Response<PingResponse>
}

data class BoletasLoteRequest(
    val boletas: List<BoletaDto>
)

data class BoletasLoteResponse(
    val success: Boolean,
    val boletas: List<BoletaDto>,
    val total: Int,
    val errors: List<String>
)

data class PingResponse(
    val status: String,
    val message: String
)