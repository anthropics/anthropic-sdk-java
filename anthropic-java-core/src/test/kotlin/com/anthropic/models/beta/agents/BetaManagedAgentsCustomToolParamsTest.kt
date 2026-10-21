package com.anthropic.models.beta.agents

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsCustomToolParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsCustomToolParams =
            BetaManagedAgentsCustomToolParams.builder()
                .description("x")
                .inputSchema(
                    BetaManagedAgentsCustomToolInputSchema.builder()
                        .properties(
                            BetaManagedAgentsCustomToolInputSchema.Properties.builder()
                                .putAdditionalProperty("foo", JsonValue.from("bar"))
                                .build()
                        )
                        .addRequired("string")
                        .build()
                )
                .name("x")
                .build()

        assertThat(betaManagedAgentsCustomToolParams.description()).isEqualTo("x")
        assertThat(betaManagedAgentsCustomToolParams.inputSchema())
            .isEqualTo(
                BetaManagedAgentsCustomToolInputSchema.builder()
                    .properties(
                        BetaManagedAgentsCustomToolInputSchema.Properties.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .addRequired("string")
                    .build()
            )
        assertThat(betaManagedAgentsCustomToolParams.name()).isEqualTo("x")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsCustomToolParams =
            BetaManagedAgentsCustomToolParams.builder()
                .description("x")
                .inputSchema(
                    BetaManagedAgentsCustomToolInputSchema.builder()
                        .properties(
                            BetaManagedAgentsCustomToolInputSchema.Properties.builder()
                                .putAdditionalProperty("foo", JsonValue.from("bar"))
                                .build()
                        )
                        .addRequired("string")
                        .build()
                )
                .name("x")
                .build()

        val roundtrippedBetaManagedAgentsCustomToolParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsCustomToolParams),
                jacksonTypeRef<BetaManagedAgentsCustomToolParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsCustomToolParams)
            .isEqualTo(betaManagedAgentsCustomToolParams)
    }
}
