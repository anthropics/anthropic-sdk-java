// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.skills

import com.anthropic.core.MultipartField
import java.io.InputStream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SkillCreateParamsTest {

    @Test
    fun create() {
        SkillCreateParams.builder()
            .addFile(MultipartField.of("Example data".byteInputStream()))
            .displayName("display_name")
            .build()
    }

    @Test
    fun body() {
        val params =
            SkillCreateParams.builder()
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
