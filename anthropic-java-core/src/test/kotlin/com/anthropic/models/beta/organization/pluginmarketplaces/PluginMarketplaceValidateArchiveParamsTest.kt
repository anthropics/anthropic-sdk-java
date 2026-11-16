package com.anthropic.models.beta.organization.pluginmarketplaces

import com.anthropic.core.MultipartField
import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import java.io.InputStream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PluginMarketplaceValidateArchiveParamsTest {

    @Test
    fun create() {
        PluginMarketplaceValidateArchiveParams.builder()
            .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
            .archive("Example data".byteInputStream())
            .build()
    }

    @Test
    fun headers() {
        val params =
            PluginMarketplaceValidateArchiveParams.builder()
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .archive("Example data".byteInputStream())
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder()
                    .put(
                        "anthropic-beta",
                        listOf("computer-use-2024-10-22", "ce-plugins-2026-09-01").joinToString(","),
                    )
                    .build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params =
            PluginMarketplaceValidateArchiveParams.builder()
                .archive("Example data".byteInputStream())
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            PluginMarketplaceValidateArchiveParams.builder()
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .archive("Example data".byteInputStream())
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
                mapOf("archive" to MultipartField.of("Example data".byteInputStream())).mapValues {
                    (_, field) ->
                    field.map { (it as? ByteArray)?.inputStream() ?: it }
                }
            )
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            PluginMarketplaceValidateArchiveParams.builder()
                .archive("Example data".byteInputStream())
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
                mapOf("archive" to MultipartField.of("Example data".byteInputStream())).mapValues {
                    (_, field) ->
                    field.map { (it as? ByteArray)?.inputStream() ?: it }
                }
            )
    }
}
