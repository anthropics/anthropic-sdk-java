// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.skills

import com.anthropic.core.MultipartField
import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import java.io.InputStream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SkillCreateParamsTest {

    @Test
    fun create() {
        SkillCreateParams.builder()
            .addBeta(AnthropicBeta.of("string"))
            .displayTitle("display_title")
            .addFile("some content".byteInputStream())
            .build()
    }

    @Test
    fun headers() {
        val params =
            SkillCreateParams.builder()
                .addBeta(AnthropicBeta.of("string"))
                .displayTitle("display_title")
                .addFile("some content".byteInputStream())
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().put("anthropic-beta", "string").build())
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
                .addBeta(AnthropicBeta.of("string"))
                .displayTitle("display_title")
                .addFile("some content".byteInputStream())
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
                                .value(listOf("some content".byteInputStream()))
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
