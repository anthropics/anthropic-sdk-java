package com.anthropic.client.okhttp

import com.anthropic.backends.Backend
import org.assertj.core.api.Assertions.assertThatNoException
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

internal class AnthropicOkHttpClientTest {
    companion object {
        private const val BASE_URL = "https://api.example.com"
        private const val API_KEY = "test-api-key-123456789"
        private const val AUTH_TOKEN = "test-auth-token-987654321"
    }

    private class TestBackend : Backend {
        override fun baseUrl(): String = BASE_URL

        override fun close() {}
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

    private fun createBuilder(): AnthropicOkHttpClient.Builder = AnthropicOkHttpClient.builder()
}
