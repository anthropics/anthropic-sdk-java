// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.files

import com.anthropic.core.MultipartField
import com.anthropic.core.http.Headers
import java.io.InputStream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class FileUploadParamsTest {

    @Test
    fun create() {
        FileUploadParams.builder()
            .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
            .file("Example data".byteInputStream())
            .expiresInSeconds(3600L)
            .build()
    }

    @Test
    fun headers() {
        val params =
            FileUploadParams.builder()
                .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                .file("Example data".byteInputStream())
                .expiresInSeconds(3600L)
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder()
                    .put("anthropic-workspace-id", "wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params = FileUploadParams.builder().file("Example data".byteInputStream()).build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            FileUploadParams.builder()
                .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                .file("Example data".byteInputStream())
                .expiresInSeconds(3600L)
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
                        "file" to MultipartField.of("Example data".byteInputStream()),
                        "expires_in_seconds" to MultipartField.of(3600L),
                    )
                    .mapValues { (_, field) ->
                        field.map { (it as? ByteArray)?.inputStream() ?: it }
                    }
            )
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params = FileUploadParams.builder().file("Example data".byteInputStream()).build()

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
                mapOf("file" to MultipartField.of("Example data".byteInputStream())).mapValues {
                    (_, field) ->
                    field.map { (it as? ByteArray)?.inputStream() ?: it }
                }
            )
    }
}
