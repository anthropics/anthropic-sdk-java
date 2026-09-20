package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCompactionBlockTest {

    @Test
    fun create() {
        val betaCompactionBlock =
            BetaCompactionBlock.builder()
                .content("content")
                .encryptedContent("encrypted_content")
                .signature("signature")
                .addReferenceAdditionToolChange("name")
                .build()

        assertThat(betaCompactionBlock.content()).contains("content")
        assertThat(betaCompactionBlock.encryptedContent()).contains("encrypted_content")
        assertThat(betaCompactionBlock.signature()).contains("signature")
        assertThat(betaCompactionBlock.toolChanges().getOrNull())
            .containsExactly(
                BetaCompactionBlock.ToolChange.ofAddition(
                    BetaResponseToolAdditionBlock.builder().referenceTool("name").build()
                )
            )
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseBetaCompactionBlock =
            BetaCompactionBlock.builder()
                .content("content")
                .encryptedContent("encrypted_content")
                .build()

        val betaCompactionBlock =
            baseBetaCompactionBlock
                .toBuilder()
                .addToolChange(
                    BetaCompactionBlock.ToolChange.ofAddition(
                        BetaResponseToolAdditionBlock.builder().referenceTool("name").build()
                    )
                )
                .build()

        assertThat(betaCompactionBlock.toolChanges().getOrNull())
            .containsExactly(
                BetaCompactionBlock.ToolChange.ofAddition(
                    BetaResponseToolAdditionBlock.builder().referenceTool("name").build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCompactionBlock =
            BetaCompactionBlock.builder()
                .content("content")
                .encryptedContent("encrypted_content")
                .signature("signature")
                .addReferenceAdditionToolChange("name")
                .build()

        val roundtrippedBetaCompactionBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCompactionBlock),
                jacksonTypeRef<BetaCompactionBlock>(),
            )

        assertThat(roundtrippedBetaCompactionBlock).isEqualTo(betaCompactionBlock)
    }
}
