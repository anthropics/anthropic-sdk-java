package com.anthropic.auth

import com.anthropic.core.auth.FileIdentityTokenProvider
import java.nio.file.Path
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir

internal class IdentityTokenProviderTest {

    @Test
    fun identityTokenFileReadsFromFile(@TempDir tempDir: Path) {
        val tokenFile = tempDir.resolve("token")
        tokenFile.toFile().writeText("file-jwt-token")

        val provider = FileIdentityTokenProvider(tokenFile.toString())

        assertThat(provider.get()).isEqualTo("file-jwt-token")
    }

    @Test
    fun identityTokenFileReadsFromFileAsync(@TempDir tempDir: Path) {
        val tokenFile = tempDir.resolve("token")
        tokenFile.toFile().writeText("file-jwt-token")

        val provider = FileIdentityTokenProvider(tokenFile.toString())

        assertThat(provider.getAsync().get()).isEqualTo("file-jwt-token")
    }

    @Test
    fun identityTokenFileReReadsOnEachCall(@TempDir tempDir: Path) {
        val tokenFile = tempDir.resolve("token")
        tokenFile.toFile().writeText("token-v1")

        val provider = FileIdentityTokenProvider(tokenFile.toString())
        assertThat(provider.get()).isEqualTo("token-v1")

        tokenFile.toFile().writeText("token-v2")
        assertThat(provider.get()).isEqualTo("token-v2")
    }

    @Test
    fun identityTokenFileThrowsWhenFileMissing() {
        val provider = FileIdentityTokenProvider("/nonexistent/path/to/token")

        assertThatThrownBy { provider.get() }.isInstanceOf(java.io.IOException::class.java)
    }
}
