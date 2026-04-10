// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MessageParamTest {

    @Test
    fun create() {
        val messageParam =
            MessageParam.builder()
                .contentOfBlockParams(
                    listOf(
                        ContentBlockParam.ofText(
                            TextBlockParam.builder()
                                .text("What is a quaternion?")
                                .cacheControl(
                                    CacheControlEphemeral.builder()
                                        .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                                        .build()
                                )
                                .addCitation(
                                    CitationCharLocationParam.builder()
                                        .citedText("cited_text")
                                        .documentIndex(0L)
                                        .documentTitle("x")
                                        .endCharIndex(0L)
                                        .startCharIndex(0L)
                                        .build()
                                )
                                .build()
                        )
                    )
                )
                .role(MessageParam.Role.USER)
                .build()

        assertThat(messageParam.content())
            .isEqualTo(
                MessageParam.Content.ofBlockParams(
                    listOf(
                        ContentBlockParam.ofText(
                            TextBlockParam.builder()
                                .text("What is a quaternion?")
                                .cacheControl(
                                    CacheControlEphemeral.builder()
                                        .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                                        .build()
                                )
                                .addCitation(
                                    CitationCharLocationParam.builder()
                                        .citedText("cited_text")
                                        .documentIndex(0L)
                                        .documentTitle("x")
                                        .endCharIndex(0L)
                                        .startCharIndex(0L)
                                        .build()
                                )
                                .build()
                        )
                    )
                )
            )
        assertThat(messageParam.role()).isEqualTo(MessageParam.Role.USER)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val messageParam =
            MessageParam.builder()
                .contentOfBlockParams(
                    listOf(
                        ContentBlockParam.ofText(
                            TextBlockParam.builder()
                                .text("What is a quaternion?")
                                .cacheControl(
                                    CacheControlEphemeral.builder()
                                        .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                                        .build()
                                )
                                .addCitation(
                                    CitationCharLocationParam.builder()
                                        .citedText("cited_text")
                                        .documentIndex(0L)
                                        .documentTitle("x")
                                        .endCharIndex(0L)
                                        .startCharIndex(0L)
                                        .build()
                                )
                                .build()
                        )
                    )
                )
                .role(MessageParam.Role.USER)
                .build()

        val roundtrippedMessageParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(messageParam),
                jacksonTypeRef<MessageParam>(),
            )

        assertThat(roundtrippedMessageParam).isEqualTo(messageParam)
    }
}
