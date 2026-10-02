package com.anthropic.models.beta.organization.plugins.versions

import com.anthropic.core.MultipartField
import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import java.io.InputStream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class VersionCreateParamsTest {

    @Test
    fun create() {
        VersionCreateParams.builder()
            .pluginId("plugin_id")
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .addFile("Example data".byteInputStream())
            .releaseNotes("release_notes")
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            VersionCreateParams.builder()
                .pluginId("plugin_id")
                .addFile("Example data".byteInputStream())
                .build()

        assertThat(params._pathParam(0)).isEqualTo("plugin_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            VersionCreateParams.builder()
                .pluginId("plugin_id")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .addFile("Example data".byteInputStream())
                .releaseNotes("release_notes")
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder()
                    .put("anthropic-beta", listOf("message-batches-2024-09-24").joinToString(","))
                    .build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params =
            VersionCreateParams.builder()
                .pluginId("plugin_id")
                .addFile("Example data".byteInputStream())
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            VersionCreateParams.builder()
                .pluginId("plugin_id")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .addFile("Example data".byteInputStream())
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
                        "release_notes" to MultipartField.of("release_notes"),
                    )
                    .mapValues { (_, field) ->
                        field.map { (it as? ByteArray)?.inputStream() ?: it }
                    }
            )
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            VersionCreateParams.builder()
                .pluginId("plugin_id")
                .addFile("Example data".byteInputStream())
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
                                .build()
                    )
                    .mapValues { (_, field) ->
                        field.map { (it as? ByteArray)?.inputStream() ?: it }
                    }
            )
    }
}
