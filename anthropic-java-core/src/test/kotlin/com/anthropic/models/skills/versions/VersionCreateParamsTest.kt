// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.skills.versions

import com.anthropic.core.MultipartField
import java.io.InputStream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class VersionCreateParamsTest {

    @Test
    fun create() {
        VersionCreateParams.builder()
            .skillId("skill_id")
            .addFile(MultipartField.of("Example data".byteInputStream()))
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            VersionCreateParams.builder()
                .skillId("skill_id")
                .addFile(MultipartField.of("Example data".byteInputStream()))
                .build()

        assertThat(params._pathParam(0)).isEqualTo("skill_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun body() {
        val params =
            VersionCreateParams.builder()
                .skillId("skill_id")
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

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            VersionCreateParams.builder()
                .skillId("skill_id")
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
