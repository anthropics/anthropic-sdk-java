package com.anthropic.auth

import com.anthropic.core.auth.FileIdentityTokenProvider
import java.nio.file.Path
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
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

        assertThat(provider.getAsync { it.run() }.get()).isEqualTo("file-jwt-token")
    }

    @Test
    fun identityTokenFileReadsInExecutorAsync(@TempDir tempDir: Path) {
        val tokenFile = tempDir.resolve("token")
        tokenFile.toFile().writeText("file-jwt-token")
        val executor = Executors.newSingleThreadExecutor { Thread(it, "executor") }
        val continuationChained = CompletableFuture<Void?>()
        executor.execute { continuationChained.join() }

        val readThreadName =
            FileIdentityTokenProvider(tokenFile.toString()).getAsync(executor).thenApply {
                Thread.currentThread().name
            }
        continuationChained.complete(null)

        assertThat(readThreadName.get(1, TimeUnit.MINUTES)).isEqualTo("executor")
        executor.shutdown()
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
