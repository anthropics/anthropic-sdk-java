package com.anthropic.client.okhttp

import com.anthropic.backends.Backend
import com.anthropic.errors.AnthropicException
import com.anthropic.models.beta.messages.ThreadCreateParams
import org.assertj.core.api.Assertions.assertThatNoException
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

internal class AnthropicOkHttpClientTest {
    companion object {
        private const val BASE_URL = "https://api.example.com"
        private const val API_KEY = "test-api-key-123456789"
        private const val AUTH_TOKEN = "test-auth-token-987654321"
    }

    private class TestBackend(private val supportsMessageThreads: Boolean = true) : Backend {
        override fun baseUrl(): String = BASE_URL

        override fun supportsMessageThreads(): Boolean = supportsMessageThreads

        override fun close() {}
    }

    private val threadParams =
        ThreadCreateParams.builder().model("claude-sonnet-4-5").maxTokens(1024).build()

    @Test
    fun messageThreadsFollowTheBackend() {
        val threaded = createBuilder().backend(TestBackend()).build()
        val unthreaded =
            createBuilder().backend(TestBackend(supportsMessageThreads = false)).build()

        assertThatNoException().isThrownBy { threaded.beta().messages().thread(threadParams) }
        assertThatThrownBy { unthreaded.beta().messages().thread(threadParams) }
            .isExactlyInstanceOf(AnthropicException::class.java)
    }

    @Test
    fun messageThreadsAreAvailableOnTheDefaultBackend() {
        val client = AnthropicOkHttpClient.builder().apiKey("test-api-key").build()

        assertThatNoException().isThrownBy { client.beta().messages().thread(threadParams) }
    }

    @Test
    fun backendExplicit() {
        assertThatNoException().isThrownBy { createBuilder().backend(TestBackend()).build() }
    }

    @Test
    fun backendDefaultFromApiKey() {
        assertThatNoException().isThrownBy { createBuilder().apiKey(API_KEY).build() }
    }

    @Test
    fun backendDefaultFromAuthToken() {
        assertThatNoException().isThrownBy { createBuilder().apiKey(AUTH_TOKEN).build() }
    }

    @Test
    fun backendDefaultFromBaseUrlAndApiKey() {
        assertThatNoException().isThrownBy {
            createBuilder().apiKey(API_KEY).baseUrl(BASE_URL).build()
        }
    }

    @Test
    fun backendDefaultFromBaseUrlAndAuthToken() {
        assertThatNoException().isThrownBy {
            createBuilder().authToken(AUTH_TOKEN).baseUrl(BASE_URL).build()
        }
    }

    @Test
    fun backendDefaultWhenNothingSet() {
        assertThatNoException().isThrownBy { createBuilder().build() }
    }

    @Test
    fun backendAlreadySetApiKey() {
        val builder = createBuilder().apiKey(API_KEY)

        assertThatThrownBy { builder.backend(TestBackend()) }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessageStartingWith("Default backend already set")
    }

    @Test
    fun backendAlreadySetAuthToken() {
        val builder = createBuilder().authToken(AUTH_TOKEN)

        assertThatThrownBy { builder.backend(TestBackend()) }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessageStartingWith("Default backend already set")
    }

    @Test
    fun backendAlreadySetBaseUrl() {
        val builder = createBuilder().baseUrl(BASE_URL)

        assertThatThrownBy { builder.backend(TestBackend()) }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessageStartingWith("Default backend already set")
    }

    @Test
    fun credentialsAfterBackendSet() {
        val builder = createBuilder().backend(TestBackend())

        assertThatThrownBy { builder.apiKey(API_KEY) }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessageStartingWith("Backend already set")
        assertThatThrownBy { builder.authToken(AUTH_TOKEN) }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessageStartingWith("Backend already set")
        assertThatThrownBy { builder.fromEnv() }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessageStartingWith("Backend already set")
    }

    private fun createBuilder(): AnthropicOkHttpClient.Builder = AnthropicOkHttpClient.builder()
}
