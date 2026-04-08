// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsCustomToolTest {

    @Test
    fun create() {
        val betaManagedAgentsCustomTool =
            BetaManagedAgentsCustomTool.builder()
                .description("description")
                .inputSchema(
                    BetaManagedAgentsCustomToolInputSchema.builder()
                        .properties(
                            BetaManagedAgentsCustomToolInputSchema.Properties.builder()
                                .putAdditionalProperty("foo", JsonValue.from("bar"))
                                .build()
                        )
                        .addRequired("string")
                        .type(BetaManagedAgentsCustomToolInputSchema.Type.OBJECT)
                        .build()
                )
                .name("name")
                .type(BetaManagedAgentsCustomTool.Type.CUSTOM)
                .build()

        assertThat(betaManagedAgentsCustomTool.description()).isEqualTo("description")
        assertThat(betaManagedAgentsCustomTool.inputSchema())
            .isEqualTo(
                BetaManagedAgentsCustomToolInputSchema.builder()
                    .properties(
                        BetaManagedAgentsCustomToolInputSchema.Properties.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .addRequired("string")
                    .type(BetaManagedAgentsCustomToolInputSchema.Type.OBJECT)
                    .build()
            )
        assertThat(betaManagedAgentsCustomTool.name()).isEqualTo("name")
        assertThat(betaManagedAgentsCustomTool.type())
            .isEqualTo(BetaManagedAgentsCustomTool.Type.CUSTOM)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsCustomTool =
            BetaManagedAgentsCustomTool.builder()
                .description("description")
                .inputSchema(
                    BetaManagedAgentsCustomToolInputSchema.builder()
                        .properties(
                            BetaManagedAgentsCustomToolInputSchema.Properties.builder()
                                .putAdditionalProperty("foo", JsonValue.from("bar"))
                                .build()
                        )
                        .addRequired("string")
                        .type(BetaManagedAgentsCustomToolInputSchema.Type.OBJECT)
                        .build()
                )
                .name("name")
                .type(BetaManagedAgentsCustomTool.Type.CUSTOM)
                .build()

        val roundtrippedBetaManagedAgentsCustomTool =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsCustomTool),
                jacksonTypeRef<BetaManagedAgentsCustomTool>(),
            )

        assertThat(roundtrippedBetaManagedAgentsCustomTool).isEqualTo(betaManagedAgentsCustomTool)
    }
}
