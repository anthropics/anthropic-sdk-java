package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaResponseToolTest {

    @Test
    fun create() {
        val betaResponseTool =
            BetaResponseTool.builder()
                .inputSchema(
                    BetaResponseToolInputSchema.builder()
                        .properties(
                            BetaResponseToolInputSchema.Properties.builder()
                                .putAdditionalProperty("location", JsonValue.from("bar"))
                                .putAdditionalProperty("unit", JsonValue.from("bar"))
                                .build()
                        )
                        .addRequired("location")
                        .build()
                )
                .name("name")
                .addAllowedCaller(BetaResponseTool.AllowedCaller.DIRECT)
                .deferLoading(true)
                .description("Get the current weather in a given location")
                .eagerInputStreaming(true)
                .addInputExample(
                    BetaResponseTool.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .strict(true)
                .type(BetaResponseTool.Type.CUSTOM)
                .build()

        assertThat(betaResponseTool.inputSchema())
            .isEqualTo(
                BetaResponseToolInputSchema.builder()
                    .properties(
                        BetaResponseToolInputSchema.Properties.builder()
                            .putAdditionalProperty("location", JsonValue.from("bar"))
                            .putAdditionalProperty("unit", JsonValue.from("bar"))
                            .build()
                    )
                    .addRequired("location")
                    .build()
            )
        assertThat(betaResponseTool.name()).isEqualTo("name")
        assertThat(betaResponseTool.allowedCallers().getOrNull())
            .containsExactly(BetaResponseTool.AllowedCaller.DIRECT)
        assertThat(betaResponseTool.deferLoading()).contains(true)
        assertThat(betaResponseTool.description())
            .contains("Get the current weather in a given location")
        assertThat(betaResponseTool.eagerInputStreaming()).contains(true)
        assertThat(betaResponseTool.inputExamples().getOrNull())
            .containsExactly(
                BetaResponseTool.InputExample.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
        assertThat(betaResponseTool.strict()).contains(true)
        assertThat(betaResponseTool.type()).contains(BetaResponseTool.Type.CUSTOM)
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseBetaResponseTool =
            BetaResponseTool.builder()
                .inputSchema(BetaResponseToolInputSchema.builder().build())
                .name("name")
                .build()

        val betaResponseTool =
            baseBetaResponseTool
                .toBuilder()
                .addAllowedCaller(BetaResponseTool.AllowedCaller.DIRECT)
                .addInputExample(
                    BetaResponseTool.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .build()

        assertThat(betaResponseTool.allowedCallers().getOrNull())
            .containsExactly(BetaResponseTool.AllowedCaller.DIRECT)
        assertThat(betaResponseTool.inputExamples().getOrNull())
            .containsExactly(
                BetaResponseTool.InputExample.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaResponseTool =
            BetaResponseTool.builder()
                .inputSchema(
                    BetaResponseToolInputSchema.builder()
                        .properties(
                            BetaResponseToolInputSchema.Properties.builder()
                                .putAdditionalProperty("location", JsonValue.from("bar"))
                                .putAdditionalProperty("unit", JsonValue.from("bar"))
                                .build()
                        )
                        .addRequired("location")
                        .build()
                )
                .name("name")
                .addAllowedCaller(BetaResponseTool.AllowedCaller.DIRECT)
                .deferLoading(true)
                .description("Get the current weather in a given location")
                .eagerInputStreaming(true)
                .addInputExample(
                    BetaResponseTool.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .strict(true)
                .type(BetaResponseTool.Type.CUSTOM)
                .build()

        val roundtrippedBetaResponseTool =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaResponseTool),
                jacksonTypeRef<BetaResponseTool>(),
            )

        assertThat(roundtrippedBetaResponseTool).isEqualTo(betaResponseTool)
    }
}
