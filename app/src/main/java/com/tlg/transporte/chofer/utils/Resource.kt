/**
 * Clase wrapper para estados de recursos
 * Path: app/src/main/java/com/tlg/transporte/chofer/utils/Resource.kt
 * Última modificación: 2026-02-05 14:50
 * Dev: cpalacios01@gmail.com | +595994648273
 */

package com.tlg.transporte.chofer.utils

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T> : Resource<T>()
}