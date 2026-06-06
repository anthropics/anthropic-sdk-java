// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsCustomToolInputSchemaTest {

    @Test
    fun create() {
        val betaManagedAgentsCustomToolInputSchema =
            BetaManagedAgentsCustomToolInputSchema.builder()
                .properties(
                    BetaManagedAgentsCustomToolInputSchema.Properties.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .addRequired("string")
                .build()

        assertThat(betaManagedAgentsCustomToolInputSchema.properties())
            .contains(
                BetaManagedAgentsCustomToolInputSchema.Properties.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
        assertThat(betaManagedAgentsCustomToolInputSchema.required().getOrNull())
            .containsExactly("string")
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseBetaManagedAgentsCustomToolInputSchema =
            BetaManagedAgentsCustomToolInputSchema.builder().build()

        val betaManagedAgentsCustomToolInputSchema =
            baseBetaManagedAgentsCustomToolInputSchema.toBuilder().addRequired("string").build()

        assertThat(betaManagedAgentsCustomToolInputSchema.required().getOrNull())
            .containsExactly("string")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsCustomToolInputSchema =
            BetaManagedAgentsCustomToolInputSchema.builder()
                .properties(
                    BetaManagedAgentsCustomToolInputSchema.Properties.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .addRequired("string")
                .build()

        val roundtrippedBetaManagedAgentsCustomToolInputSchema =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsCustomToolInputSchema),
                jacksonTypeRef<BetaManagedAgentsCustomToolInputSchema>(),
            )

        assertThat(roundtrippedBetaManagedAgentsCustomToolInputSchema)
            .isEqualTo(betaManagedAgentsCustomToolInputSchema)
    }
}
