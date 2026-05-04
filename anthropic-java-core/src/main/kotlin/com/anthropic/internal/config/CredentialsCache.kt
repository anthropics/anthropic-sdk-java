package com.anthropic.internal.config

import com.anthropic.core.auth.AccessToken
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.json.JsonMapper
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption
import java.nio.file.attribute.PosixFilePermissions
import java.time.Instant

@JsonIgnoreProperties(ignoreUnknown = true)
internal data class CachedTokenData(
    @get:JsonProperty("type") @JsonProperty("type") val type: String? = null,
    @get:JsonProperty("access_token") @JsonProperty("access_token") val accessToken: String? = null,
    @get:JsonProperty("expires_at") @JsonProperty("expires_at") val expiresAt: Long? = null,
    @get:JsonProperty("refresh_token")
    @JsonProperty("refresh_token")
    val refreshToken: String? = null,
)

internal open class CredentialsCache(private val path: Path, private val mapper: JsonMapper) {
    /**
     * Read the full credential record without filtering expired tokens.
     *
     * Returns null only when the file is absent, unreadable, or malformed JSON. Callers decide what
     * to do with an expired `access_token` or a missing `refresh_token` — this layer does not
     * interpret the record.
     */
    internal fun readRecord(): CachedTokenData? {
        if (!Files.exists(path)) {
            return null
        }
        return try {
            mapper.readValue(path.toFile().readText(), CachedTokenData::class.java)
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Atomically write the full credential record.
     *
     * Writes to a sibling tempfile in the target directory, sets 0600 on the temp (POSIX only),
     * then `Files.move(..., ATOMIC_MOVE, REPLACE_EXISTING)`. Same-directory tempfile is required so
     * `ATOMIC_MOVE` works — cross-fs moves fall back to copy+delete and lose atomicity.
     */
    internal open fun writeRecord(record: CachedTokenData) {
        val dir = path.parent
        if (dir != null && !Files.exists(dir)) {
            Files.createDirectories(dir)
        }
        val parent = dir ?: path.toAbsolutePath().parent
        val temp = Files.createTempFile(parent, ".${path.fileName}.", ".tmp")
        try {
            val json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(record)
            temp.toFile().writeText(json)
            try {
                Files.setPosixFilePermissions(temp, PosixFilePermissions.fromString("rw-------"))
            } catch (_: UnsupportedOperationException) {}
            try {
                Files.move(
                    temp,
                    path,
                    StandardCopyOption.ATOMIC_MOVE,
                    StandardCopyOption.REPLACE_EXISTING,
                )
            } catch (_: java.nio.file.AtomicMoveNotSupportedException) {
                Files.move(temp, path, StandardCopyOption.REPLACE_EXISTING)
            }
        } catch (e: Exception) {
            try {
                Files.deleteIfExists(temp)
            } catch (_: Exception) {}
            throw e
        }
    }

    fun read(): AccessToken? {
        val record = readRecord() ?: return null
        val token = record.accessToken ?: return null
        val expiry = record.expiresAt?.let { Instant.ofEpochSecond(it) }
        return AccessToken(token, expiry)
    }
}
