// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaContainerUploadBlockParamTest {

    @Test
    fun create() {
        val betaContainerUploadBlockParam =
            BetaContainerUploadBlockParam.builder()
                .fileId("file_id")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        assertThat(betaContainerUploadBlockParam.fileId()).isEqualTo("file_id")
        assertThat(betaContainerUploadBlockParam.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaContainerUploadBlockParam =
            BetaContainerUploadBlockParam.builder()
                .fileId("file_id")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        val roundtrippedBetaContainerUploadBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaContainerUploadBlockParam),
                jacksonTypeRef<BetaContainerUploadBlockParam>(),
            )

        assertThat(roundtrippedBetaContainerUploadBlockParam)
            .isEqualTo(betaContainerUploadBlockParam)
    }
}
