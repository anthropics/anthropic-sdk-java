// JS compile-only stubs for com.fasterxml.jackson.core.util
package com.fasterxml.jackson.core.util

class Version(
    val majorVersion: Int = 0,
    val minorVersion: Int = 0,
    val patchLevel: Int = 0,
    private val snapshot: String? = null,
) {
    override fun toString(): String = "$majorVersion.$minorVersion.$patchLevel"
}

object VersionUtil {
    fun parseVersion(version: String, groupId: String?, artifactId: String?): Version {
        val parts = version.split(".")
        return Version(
            parts.getOrNull(0)?.toIntOrNull() ?: 0,
            parts.getOrNull(1)?.toIntOrNull() ?: 0,
            parts.getOrNull(2)?.toIntOrNull() ?: 0,
        )
    }
}
