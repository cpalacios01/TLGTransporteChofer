/**
 * DTO para respuesta de login
 * Path: app/src/main/java/com/tlg/transporte/chofer/data/remote/dto/LoginResponse.kt
 * Última modificación: 2026-02-05 14:40
 * Dev: cpalacios01@gmail.com | +595994648273
 */

package com.tlg.transporte.chofer.data.remote.dto

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    val user: UserDto,
    val token: String,
    @SerializedName("token_type")
    val tokenType: String
)

data class UserDto(
    val id: Int,
    val nombre: String,
    val email: String,
    @SerializedName("role_id")
    val roleId: Int,
    @SerializedName("role_name")
    val roleName: String,
    @SerializedName("bus_id")
    val busId: Int?,
    val activo: Boolean
)