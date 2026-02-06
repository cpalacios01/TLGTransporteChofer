/**
 * DTO para solicitud de login
 * Path: app/src/main/java/com/tlg/transporte/chofer/data/remote/dto/LoginRequest.kt
 * Última modificación: 2026-02-05 14:40
 * Dev: cpalacios01@gmail.com | +595994648273
 */

package com.tlg.transporte.chofer.data.remote.dto

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    val email: String,
    val password: String,
    @SerializedName("device_name")
    val deviceName: String = "Mibo MP200"
)