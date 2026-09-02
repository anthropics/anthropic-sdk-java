// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.skills

import com.anthropic.core.MultipartField
import com.anthropic.core.http.Headers
import java.io.InputStream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SkillCreateParamsTest {

    @Test
    fun create() {
        SkillCreateParams.builder()
            .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
            .addFile(MultipartField.of("Example data".byteInputStream()))
            .displayName("display_name")
            .build()
    }

    @Test
    fun headers() {
        val params =
            SkillCreateParams.builder()
                .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                .addFile(MultipartField.of("Example data".byteInputStream()))
                .displayName("display_name")
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
        val params =
            SkillCreateParams.builder()
                .addFile(MultipartField.of("Example data".byteInputStream()))
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            SkillCreateParams.builder()
                .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                .addFile(MultipartField.of("Example data".byteInputStream()))
                .displayName("display_name")
                .build()

        val body = params._body()

        assertThat(body.filterValues { !it.value.isNull() })
            .usingRecursiveComparison()
            .withEqualsForType(
                { a, b -> a.readBytes() contentEquals b.readBytes() },
                InputStream::class.java,
            )
            .isEqualTo(
                mapOf(
                    "display_name" to MultipartField.of("display_name"),
                    "files" to
                        MultipartField.of(
                            listOf(MultipartField.of("Example data".byteInputStream()))
                        ),
                )
            )
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            SkillCreateParams.builder()
                .addFile(MultipartField.of("Example data".byteInputStream()))
                .build()

        val body = params._body()

        assertThat(body.filterValues { !it.value.isNull() })
            .usingRecursiveComparison()
            .withEqualsForType(
                { a, b -> a.readBytes() contentEquals b.readBytes() },
                InputStream::class.java,
            )
            .isEqualTo(
                mapOf(
                    "files" to
                        MultipartField.of(
                            listOf(MultipartField.of("Example data".byteInputStream()))
                        )
                )
            )
    }
}
