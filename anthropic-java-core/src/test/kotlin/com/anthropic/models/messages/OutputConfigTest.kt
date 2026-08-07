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
                .effort(OutputConfig.Effort.LOW)
                .format(
                    JsonOutputFormat.of(
                        JsonOutputFormat.Schema.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                )
                .build()

        assertThat(outputConfig.effort()).contains(OutputConfig.Effort.LOW)
        assertThat(outputConfig.format())
            .contains(
                JsonOutputFormat.of(
                    JsonOutputFormat.Schema.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val outputConfig =
            OutputConfig.builder()
                .effort(OutputConfig.Effort.LOW)
                .format(
                    JsonOutputFormat.of(
                        JsonOutputFormat.Schema.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
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
