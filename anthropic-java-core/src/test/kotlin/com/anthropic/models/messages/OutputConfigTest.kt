// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class OutputConfigTest {

    @Test
    fun create() {
        val outputConfig =
            OutputConfig.builder()
                .format(
                    JsonOutputFormat.builder()
                        .schema(
                            JsonOutputFormat.Schema.builder()
                                .putAdditionalProperty("foo", JsonValue.from("bar"))
                                .build()
                        )
                        .build()
                )
                .build()

        assertThat(outputConfig.format())
            .contains(
                JsonOutputFormat.builder()
                    .schema(
                        JsonOutputFormat.Schema.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val outputConfig =
            OutputConfig.builder()
                .format(
                    JsonOutputFormat.builder()
                        .schema(
                            JsonOutputFormat.Schema.builder()
                                .putAdditionalProperty("foo", JsonValue.from("bar"))
                                .build()
                        )
                        .build()
                )
                .build()

        val roundtrippedOutputConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(outputConfig),
                jacksonTypeRef<OutputConfig>(),
            )

        assertThat(roundtrippedOutputConfig).isEqualTo(outputConfig)
    }
}
