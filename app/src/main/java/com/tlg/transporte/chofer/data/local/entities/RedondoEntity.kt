/**
 * Entidad de redondo para Room Database
 * Path: app/src/main/java/com/tlg/transporte/chofer/data/local/entities/RedondoEntity.kt
 * Última modificación: 2026-02-05 14:30
 * Dev: cpalacios01@gmail.com | +595994648273
 */

package com.tlg.transporte.chofer.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "redondos")
data class RedondoEntity(
    @PrimaryKey(autoGenerate = true)
    val localId: Long = 0,
    val serverId: Int?,
    val codigoUnico: String?,
    val fechaSalida: String,
    val choferId: Int,
    val busId: Int,
    val itinerarioId: Int,
    val montoInicial: Double,
    val fechaRegreso: String?,
    val estado: String, // abierto, cerrado, rendido
    val sincronizado: Boolean = false,
    val createdAt: String?,
    val updatedAt: String?
)