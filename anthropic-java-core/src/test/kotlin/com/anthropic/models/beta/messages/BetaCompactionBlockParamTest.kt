package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCompactionBlockParamTest {

    @Test
    fun create() {
        val betaCompactionBlockParam =
            BetaCompactionBlockParam.builder()
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .content("content")
                .encryptedContent("encrypted_content")
                .signature("signature")
                .addToolChange(
                    BetaRequestToolAdditionBlock.builder()
                        .referenceTool("name")
                        .cacheControl(
                            BetaCacheControlEphemeral.builder()
                                .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                                .build()
                        )
                        .build()
                )
                .build()

        assertThat(betaCompactionBlockParam.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
        assertThat(betaCompactionBlockParam.content()).contains("content")
        assertThat(betaCompactionBlockParam.encryptedContent()).contains("encrypted_content")
        assertThat(betaCompactionBlockParam.signature()).contains("signature")
        assertThat(betaCompactionBlockParam.toolChanges().getOrNull())
            .containsExactly(
                BetaCompactionBlockParam.ToolChange.ofAddition(
                    BetaRequestToolAdditionBlock.builder()
                        .referenceTool("name")
                        .cacheControl(
                            BetaCacheControlEphemeral.builder()
                                .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                                .build()
                        )
                        .build()
                )
            )
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseBetaCompactionBlockParam = BetaCompactionBlockParam.builder().build()

        val betaCompactionBlockParam =
            baseBetaCompactionBlockParam
                .toBuilder()
                .addToolChange(
                    BetaCompactionBlockParam.ToolChange.ofAddition(
                        BetaRequestToolAdditionBlock.builder()
                            .referenceTool("name")
                            .cacheControl(
                                BetaCacheControlEphemeral.builder()
                                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                                    .build()
                            )
                            .build()
                    )
                )
                .build()

        assertThat(betaCompactionBlockParam.toolChanges().getOrNull())
            .containsExactly(
                BetaCompactionBlockParam.ToolChange.ofAddition(
                    BetaRequestToolAdditionBlock.builder()
                        .referenceTool("name")
                        .cacheControl(
                            BetaCacheControlEphemeral.builder()
                                .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                                .build()
                        )
                        .build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCompactionBlockParam =
            BetaCompactionBlockParam.builder()
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .content("content")
                .encryptedContent("encrypted_content")
                .signature("signature")
                .addToolChange(
                    BetaRequestToolAdditionBlock.builder()
                        .referenceTool("name")
                        .cacheControl(
                            BetaCacheControlEphemeral.builder()
                                .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                                .build()
                        )
                        .build()
                )
                .build()

        val roundtrippedBetaCompactionBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCompactionBlockParam),
                jacksonTypeRef<BetaCompactionBlockParam>(),
            )

        assertThat(roundtrippedBetaCompactionBlockParam).isEqualTo(betaCompactionBlockParam)
    }
}
