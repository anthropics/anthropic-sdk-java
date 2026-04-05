// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.skills

import kotlinx.kmp.util.core.MultipartField
import kotlinx.kmp.util.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import java.io.InputStream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SkillCreateParamsTest {

    @Test
    fun create() {
        SkillCreateParams.builder()
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .displayTitle("display_title")
            .addFile("Example data".byteInputStream())
            .build()
    }

    @Test
    fun headers() {
        val params =
            SkillCreateParams.builder()
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .displayTitle("display_title")
                .addFile("Example data".byteInputStream())
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder().put("anthropic-beta", "message-batches-2024-09-24").build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params = SkillCreateParams.builder().build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            SkillCreateParams.builder()
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .displayTitle("display_title")
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
                        "display_title" to MultipartField.of("display_title"),
                        "files" to
                            MultipartField.builder<List<InputStream>>()
                                .value(listOf("Example data".byteInputStream()))
                                .contentType("application/octet-stream")
                                .build(),
                    )
                    .mapValues { (_, field) ->
                        field.map { (it as? ByteArray)?.inputStream() ?: it }
                    }
            )
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params = SkillCreateParams.builder().build()

        val body = params._body()

        assertThat(body.filterValues { !it.value.isNull() }).isEmpty()
    }
}
