/**
 * Entidad de gasto para Room Database
 * Path: app/src/main/java/com/tlg/transporte/chofer/data/local/entities/GastoEntity.kt
 * Última modificación: 2026-02-05 14:30
 * Dev: cpalacios01@gmail.com | +595994648273
 */

package com.tlg.transporte.chofer.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gastos")
data class GastoEntity(
    @PrimaryKey(autoGenerate = true)
    val localId: Long = 0,
    val serverId: Int?,
    val redondoLocalId: Long,
    val redondoServerId: Int?,
    val concepto: String,
    val monto: Double,
    val comprobante: String?,
    val descripcion: String?,
    val fechaHora: String,
    val sincronizado: Boolean = false,
    val createdAt: String?,
    val updatedAt: String?
)