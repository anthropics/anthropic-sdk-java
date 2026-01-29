// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class JsonOutputFormatTest {

    @Test
    fun create() {
        val jsonOutputFormat =
            JsonOutputFormat.builder()
                .schema(
                    JsonOutputFormat.Schema.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .build()

        assertThat(jsonOutputFormat.schema())
            .isEqualTo(
                JsonOutputFormat.Schema.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val jsonOutputFormat =
            JsonOutputFormat.builder()
                .schema(
                    JsonOutputFormat.Schema.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .build()

        val roundtrippedJsonOutputFormat =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(jsonOutputFormat),
                jacksonTypeRef<JsonOutputFormat>(),
            )

        assertThat(roundtrippedJsonOutputFormat).isEqualTo(jsonOutputFormat)
    }
}
