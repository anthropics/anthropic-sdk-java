// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.files

import com.anthropic.core.MultipartField
import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import java.io.InputStream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class FileUploadParamsTest {

    @Test
    fun create() {
        FileUploadParams.builder()
            .addBeta(AnthropicBeta.of("string"))
            .file("some content".byteInputStream())
            .build()
    }

    @Test
    fun headers() {
        val params =
            FileUploadParams.builder()
                .addBeta(AnthropicBeta.of("string"))
                .file("some content".byteInputStream())
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().put("anthropic-beta", "string").build())
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params = FileUploadParams.builder().file("some content".byteInputStream()).build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            FileUploadParams.builder()
                .addBeta(AnthropicBeta.of("string"))
                .file("some content".byteInputStream())
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
                mapOf("file" to MultipartField.of("some content".byteInputStream())).mapValues {
                    (_, field) ->
                    field.map { (it as? ByteArray)?.inputStream() ?: it }
                }
            )
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params = FileUploadParams.builder().file("some content".byteInputStream()).build()

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
                mapOf("file" to MultipartField.of("some content".byteInputStream())).mapValues {
                    (_, field) ->
                    field.map { (it as? ByteArray)?.inputStream() ?: it }
                }
            )
    }
}
