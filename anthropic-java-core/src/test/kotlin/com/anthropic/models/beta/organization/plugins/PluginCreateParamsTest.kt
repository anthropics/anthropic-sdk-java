package com.anthropic.models.beta.organization.plugins

import com.anthropic.core.MultipartField
import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import java.io.InputStream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PluginCreateParamsTest {

    @Test
    fun create() {
        PluginCreateParams.builder()
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .addFile("Example data".byteInputStream())
            .marketplaceId("marketplace_id")
            .releaseNotes("release_notes")
            .build()
    }

    @Test
    fun headers() {
        val params =
            PluginCreateParams.builder()
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .addFile("Example data".byteInputStream())
                .marketplaceId("marketplace_id")
                .releaseNotes("release_notes")
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder()
                    .put(
                        "anthropic-beta",
                        listOf("message-batches-2024-09-24", "ce-plugins-2026-09-01")
                            .joinToString(","),
                    )
                    .build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params = PluginCreateParams.builder().addFile("Example data".byteInputStream()).build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            PluginCreateParams.builder()
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .addFile("Example data".byteInputStream())
                .marketplaceId("marketplace_id")
                .releaseNotes("release_notes")
                .build()

        val body = params._body()

        assertThat(body.filterValues { !it.value.isNull() })
            .usingRecursiveComparison()
            // TODO(AssertJ): Replace this and the `mapValues` below with:
            // https://github.com/assertj/assertj/issues/3165
            .withEqualsForType(
                { a, b -> a.readBytes() contentEquals b.readBytes() },
                InputStream::class.java,
            )
            .isEqualTo(
                mapOf(
                        "files" to
                            MultipartField.builder<List<InputStream>>()
                                .value(listOf("Example data".byteInputStream()))
                                .contentType("application/octet-stream")
                                .build(),
                        "marketplace_id" to MultipartField.of("marketplace_id"),
                        "release_notes" to MultipartField.of("release_notes"),
                    )
                    .mapValues { (_, field) ->
                        field.map { (it as? ByteArray)?.inputStream() ?: it }
                    }
            )
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params = PluginCreateParams.builder().addFile("Example data".byteInputStream()).build()

        val body = params._body()

        assertThat(body.filterValues { !it.value.isNull() })
            .usingRecursiveComparison()
            // TODO(AssertJ): Replace this and the `mapValues` below with:
            // https://github.com/assertj/assertj/issues/3165
            .withEqualsForType(
                { a, b -> a.readBytes() contentEquals b.readBytes() },
                InputStream::class.java,
            )
            .isEqualTo(
                mapOf(
                        "files" to
                            MultipartField.builder<List<InputStream>>()
                                .value(listOf("Example data".byteInputStream()))
                                .contentType("application/octet-stream")
                                .build()
                    )
                    .mapValues { (_, field) ->
                        field.map { (it as? ByteArray)?.inputStream() ?: it }
                    }
            )
    }
}
