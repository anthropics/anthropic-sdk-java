// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAdvisorRedactedResultBlockParamTest {

    @Test
    fun create() {
        val betaAdvisorRedactedResultBlockParam =
            BetaAdvisorRedactedResultBlockParam.builder()
                .encryptedContent("encrypted_content")
                .build()

        assertThat(betaAdvisorRedactedResultBlockParam.encryptedContent())
            .isEqualTo("encrypted_content")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAdvisorRedactedResultBlockParam =
            BetaAdvisorRedactedResultBlockParam.builder()
                .encryptedContent("encrypted_content")
                .build()

        val roundtrippedBetaAdvisorRedactedResultBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAdvisorRedactedResultBlockParam),
                jacksonTypeRef<BetaAdvisorRedactedResultBlockParam>(),
            )

        assertThat(roundtrippedBetaAdvisorRedactedResultBlockParam)
            .isEqualTo(betaAdvisorRedactedResultBlockParam)
    }
}
