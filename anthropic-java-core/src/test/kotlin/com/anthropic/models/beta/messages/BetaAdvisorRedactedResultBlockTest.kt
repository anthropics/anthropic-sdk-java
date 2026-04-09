// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAdvisorRedactedResultBlockTest {

    @Test
    fun create() {
        val betaAdvisorRedactedResultBlock =
            BetaAdvisorRedactedResultBlock.builder().encryptedContent("encrypted_content").build()

        assertThat(betaAdvisorRedactedResultBlock.encryptedContent()).isEqualTo("encrypted_content")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAdvisorRedactedResultBlock =
            BetaAdvisorRedactedResultBlock.builder().encryptedContent("encrypted_content").build()

        val roundtrippedBetaAdvisorRedactedResultBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAdvisorRedactedResultBlock),
                jacksonTypeRef<BetaAdvisorRedactedResultBlock>(),
            )

        assertThat(roundtrippedBetaAdvisorRedactedResultBlock)
            .isEqualTo(betaAdvisorRedactedResultBlock)
    }
}
