// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsDocumentBlockTest {

    @Test
    fun create() {
        val betaManagedAgentsDocumentBlock =
            BetaManagedAgentsDocumentBlock.builder()
                .source(
                    BetaManagedAgentsBase64DocumentSource.builder()
                        .data("x")
                        .mediaType("x")
                        .type(BetaManagedAgentsBase64DocumentSource.Type.BASE64)
                        .build()
                )
                .type(BetaManagedAgentsDocumentBlock.Type.DOCUMENT)
                .context("context")
                .title("title")
                .build()

        assertThat(betaManagedAgentsDocumentBlock.source())
            .isEqualTo(
                BetaManagedAgentsDocumentBlock.Source.ofBase64(
                    BetaManagedAgentsBase64DocumentSource.builder()
                        .data("x")
                        .mediaType("x")
                        .type(BetaManagedAgentsBase64DocumentSource.Type.BASE64)
                        .build()
                )
            )
        assertThat(betaManagedAgentsDocumentBlock.type())
            .isEqualTo(BetaManagedAgentsDocumentBlock.Type.DOCUMENT)
        assertThat(betaManagedAgentsDocumentBlock.context()).contains("context")
        assertThat(betaManagedAgentsDocumentBlock.title()).contains("title")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDocumentBlock =
            BetaManagedAgentsDocumentBlock.builder()
                .source(
                    BetaManagedAgentsBase64DocumentSource.builder()
                        .data("x")
                        .mediaType("x")
                        .type(BetaManagedAgentsBase64DocumentSource.Type.BASE64)
                        .build()
                )
                .type(BetaManagedAgentsDocumentBlock.Type.DOCUMENT)
                .context("context")
                .title("title")
                .build()

        val roundtrippedBetaManagedAgentsDocumentBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsDocumentBlock),
                jacksonTypeRef<BetaManagedAgentsDocumentBlock>(),
            )

        assertThat(roundtrippedBetaManagedAgentsDocumentBlock)
            .isEqualTo(betaManagedAgentsDocumentBlock)
    }
}
