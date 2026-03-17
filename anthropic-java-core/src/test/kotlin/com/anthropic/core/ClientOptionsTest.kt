// File generated from our OpenAPI spec by Stainless.

package com.anthropic.core

import com.anthropic.core.http.HttpClient
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify

@ExtendWith(MockitoExtension::class)
internal class ClientOptionsTest {

    private val httpClient = mock<HttpClient>()

    @Test
    fun putHeader_canOverwriteDefaultHeader() {
        val clientOptions =
            ClientOptions.builder()
                .httpClient(httpClient)
                .putHeader("User-Agent", "My User Agent")
                .apiKey("my-anthropic-api-key")
                .build()

        assertThat(clientOptions.headers.values("User-Agent")).containsExactly("My User Agent")
    }

    @Test
    fun toBuilder_apiKeyAuthCanBeUpdated() {
        var clientOptions =
            ClientOptions.builder().httpClient(httpClient).apiKey("my-anthropic-api-key").build()

        clientOptions = clientOptions.toBuilder().apiKey("another my-anthropic-api-key").build()

        assertThat(clientOptions.headers.values("X-Api-Key"))
            .containsExactly("another my-anthropic-api-key")
    }

    @Test
    fun toBuilder_whenOriginalClientOptionsGarbageCollected_doesNotCloseOriginalClient() {
        var clientOptions =
            ClientOptions.builder().httpClient(httpClient).apiKey("my-anthropic-api-key").build()
        verify(httpClient, never()).close()

        // Overwrite the `clientOptions` variable so that the original `ClientOptions` is GC'd.
        clientOptions = clientOptions.toBuilder().build()
        System.gc()
        Thread.sleep(100)

        verify(httpClient, never()).close()
        // This exists so that `clientOptions` is still reachable.
        assertThat(clientOptions).isEqualTo(clientOptions)
    }
}
