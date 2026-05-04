package com.anthropic.client.okhttp

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FederationTokenProviderTest {

    @Test
    fun `apiKey sets static token provider`() {
        val client = AnthropicOkHttpClient.builder().apiKey("test-api-key").build()

        assertThat(client).isNotNull()
    }

    @Test
    fun `authToken sets static token provider`() {
        val client = AnthropicOkHttpClient.builder().authToken("test-auth-token").build()

        assertThat(client).isNotNull()
    }
}
