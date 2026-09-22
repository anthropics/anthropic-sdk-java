package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaResponseToolInputSchemaTest {

    @Test
    fun create() {
        val betaResponseToolInputSchema =
            BetaResponseToolInputSchema.builder()
                .properties(
                    BetaResponseToolInputSchema.Properties.builder()
                        .putAdditionalProperty("location", JsonValue.from("bar"))
                        .putAdditionalProperty("unit", JsonValue.from("bar"))
                        .build()
                )
                .addRequired("location")
                .build()

        assertThat(betaResponseToolInputSchema.properties())
            .contains(
                BetaResponseToolInputSchema.Properties.builder()
                    .putAdditionalProperty("location", JsonValue.from("bar"))
                    .putAdditionalProperty("unit", JsonValue.from("bar"))
                    .build()
            )
        assertThat(betaResponseToolInputSchema.required().getOrNull()).containsExactly("location")
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseBetaResponseToolInputSchema = BetaResponseToolInputSchema.builder().build()

        val betaResponseToolInputSchema =
            baseBetaResponseToolInputSchema.toBuilder().addRequired("location").build()

        assertThat(betaResponseToolInputSchema.required().getOrNull()).containsExactly("location")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaResponseToolInputSchema =
            BetaResponseToolInputSchema.builder()
                .properties(
                    BetaResponseToolInputSchema.Properties.builder()
                        .putAdditionalProperty("location", JsonValue.from("bar"))
                        .putAdditionalProperty("unit", JsonValue.from("bar"))
                        .build()
                )
                .addRequired("location")
                .build()

        val roundtrippedBetaResponseToolInputSchema =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaResponseToolInputSchema),
                jacksonTypeRef<BetaResponseToolInputSchema>(),
            )

        assertThat(roundtrippedBetaResponseToolInputSchema).isEqualTo(betaResponseToolInputSchema)
    }
}
