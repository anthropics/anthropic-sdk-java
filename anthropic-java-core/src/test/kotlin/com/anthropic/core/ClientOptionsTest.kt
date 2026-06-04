// File generated from our OpenAPI spec by Stainless.

package com.anthropic.core

import com.anthropic.core.http.HttpClient
import com.anthropic.core.http.Interceptor
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
                .build()

        assertThat(clientOptions.headers.values("User-Agent")).containsExactly("My User Agent")
    }

    @Test
    fun addInterceptor_appendsToInterceptors() {
        val first = Interceptor { it }
        val second = Interceptor { it }

        val clientOptions =
            ClientOptions.builder()
                .httpClient(httpClient)
                .addInterceptor(first)
                .addInterceptor(second)
                .build()

        assertThat(clientOptions.interceptors).containsExactly(first, second)
    }

    @Test
    fun toBuilder_interceptorsArePreserved() {
        val interceptor = Interceptor { it }
        var clientOptions =
            ClientOptions.builder().httpClient(httpClient).addInterceptor(interceptor).build()

        clientOptions = clientOptions.toBuilder().build()

        assertThat(clientOptions.interceptors).containsExactly(interceptor)
    }

    @Test
    fun toBuilder_whenOriginalClientOptionsGarbageCollected_doesNotCloseOriginalClient() {
        var clientOptions = ClientOptions.builder().httpClient(httpClient).build()
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
