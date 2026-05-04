package com.anthropic.config

import com.anthropic.core.jsonMapper
import com.anthropic.internal.config.CachedTokenData
import com.anthropic.internal.config.CredentialsCache
import java.nio.file.Files
import java.nio.file.Path
import java.time.Instant
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir

internal class CredentialsCacheTest {

    @Test
    fun readCachedToken(@TempDir tempDir: Path) {
        val credFile = tempDir.resolve("test.json")
        val expiresAt = Instant.now().plusSeconds(3600).epochSecond
        credFile
            .toFile()
            .writeText(
                """
        {
            "type": "access_token",
            "access_token": "cached-token",
            "expires_at": $expiresAt
        }
        """
                    .trimIndent()
            )

        val cache = CredentialsCache(credFile, jsonMapper())
        val token = cache.read()

        assertThat(token).isNotNull
        assertThat(token!!.token).isEqualTo("cached-token")
        assertThat(token.expiresAt).isNotNull
    }

    @Test
    fun readReturnsNullWhenFileMissing(@TempDir tempDir: Path) {
        val credFile = tempDir.resolve("nonexistent.json")

        val cache = CredentialsCache(credFile, jsonMapper())
        val token = cache.read()

        assertThat(token).isNull()
    }

    @Test
    fun writeCreatesFile(@TempDir tempDir: Path) {
        val credFile = tempDir.resolve("new.json")
        val cache = CredentialsCache(credFile, jsonMapper())
        val expiresAt = Instant.now().plusSeconds(3600).epochSecond

        cache.writeRecord(
            CachedTokenData(
                type = "access_token",
                accessToken = "new-token",
                expiresAt = expiresAt,
                refreshToken = null,
            )
        )

        assertThat(Files.exists(credFile)).isTrue()
        val content = credFile.toFile().readText()
        assertThat(content).contains("new-token")
        assertThat(content).contains("access_token")
    }

    @Test
    fun writeUpdatesExistingFile(@TempDir tempDir: Path) {
        val credFile = tempDir.resolve("existing.json")
        credFile.toFile().writeText("""{"access_token": "old-token"}""")
        val cache = CredentialsCache(credFile, jsonMapper())

        cache.writeRecord(
            CachedTokenData(
                type = "access_token",
                accessToken = "new-token",
                expiresAt = Instant.now().plusSeconds(3600).epochSecond,
                refreshToken = null,
            )
        )

        val content = credFile.toFile().readText()
        assertThat(content).contains("new-token")
        assertThat(content).doesNotContain("old-token")
    }

    @Test
    fun readReturnsExpiredToken(@TempDir tempDir: Path) {
        val credFile = tempDir.resolve("expired.json")
        val expiresAt = Instant.now().minusSeconds(60).epochSecond
        credFile
            .toFile()
            .writeText(
                """
            {
                "type": "access_token",
                "access_token": "expired-token",
                "expires_at": $expiresAt
            }
            """
                    .trimIndent()
            )

        val cache = CredentialsCache(credFile, jsonMapper())
        val token = cache.read()

        assertThat(token).isNotNull
        assertThat(token!!.token).isEqualTo("expired-token")
        assertThat(token.isExpired()).isTrue()
    }

    @Test
    fun readRecordReturnsFullRecord(@TempDir tempDir: Path) {
        val credFile = tempDir.resolve("full.json")
        val expiresAt = Instant.now().plusSeconds(3600).epochSecond
        credFile
            .toFile()
            .writeText(
                """
            {
                "type": "access_token",
                "access_token": "at",
                "expires_at": $expiresAt,
                "refresh_token": "rt"
            }
            """
                    .trimIndent()
            )

        val cache = CredentialsCache(credFile, jsonMapper())
        val record = cache.readRecord()

        assertThat(record).isNotNull
        assertThat(record!!.accessToken).isEqualTo("at")
        assertThat(record.refreshToken).isEqualTo("rt")
        assertThat(record.expiresAt).isEqualTo(expiresAt)
    }

    @Test
    fun readRecordReturnsNullWhenMissing(@TempDir tempDir: Path) {
        val credFile = tempDir.resolve("nope.json")

        val cache = CredentialsCache(credFile, jsonMapper())

        assertThat(cache.readRecord()).isNull()
    }

    @Test
    fun readRecordReturnsNullOnMalformedJson(@TempDir tempDir: Path) {
        val credFile = tempDir.resolve("bad.json")
        credFile.toFile().writeText("{not json")

        val cache = CredentialsCache(credFile, jsonMapper())

        assertThat(cache.readRecord()).isNull()
    }

    @Test
    fun writeRecordPersistsAllFields(@TempDir tempDir: Path) {
        val credFile = tempDir.resolve("wr.json")
        val cache = CredentialsCache(credFile, jsonMapper())
        val expiresAt = Instant.now().plusSeconds(3600).epochSecond

        cache.writeRecord(
            CachedTokenData(
                type = "access_token",
                accessToken = "at-new",
                expiresAt = expiresAt,
                refreshToken = "rt-new",
            )
        )

        val roundTrip = cache.readRecord()
        assertThat(roundTrip).isNotNull
        assertThat(roundTrip!!.type).isEqualTo("access_token")
        assertThat(roundTrip.accessToken).isEqualTo("at-new")
        assertThat(roundTrip.expiresAt).isEqualTo(expiresAt)
        assertThat(roundTrip.refreshToken).isEqualTo("rt-new")
    }

    @Test
    fun writeRecordCreatesParentDirectory(@TempDir tempDir: Path) {
        val credFile = tempDir.resolve("nested").resolve("dir").resolve("wr.json")
        val cache = CredentialsCache(credFile, jsonMapper())

        cache.writeRecord(
            CachedTokenData(
                type = "access_token",
                accessToken = "at",
                expiresAt = Instant.now().plusSeconds(60).epochSecond,
                refreshToken = null,
            )
        )

        assertThat(Files.exists(credFile)).isTrue()
    }

    @Test
    fun writeRecordSetsRestrictivePermsOnPosix(@TempDir tempDir: Path) {
        val credFile = tempDir.resolve("perms.json")
        val cache = CredentialsCache(credFile, jsonMapper())

        cache.writeRecord(
            CachedTokenData(
                type = "access_token",
                accessToken = "at",
                expiresAt = null,
                refreshToken = null,
            )
        )

        try {
            val perms = Files.getPosixFilePermissions(credFile)
            assertThat(perms)
                .containsExactlyInAnyOrder(
                    java.nio.file.attribute.PosixFilePermission.OWNER_READ,
                    java.nio.file.attribute.PosixFilePermission.OWNER_WRITE,
                )
        } catch (e: UnsupportedOperationException) {
            // Non-POSIX FS — skip, writeRecord tolerates this.
        }
    }
}
