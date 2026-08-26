// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.externalkeys

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaExternalKeyAttachedAttachmentTest {

    @Test
    fun create() {
        val betaExternalKeyAttachedAttachment = BetaExternalKeyAttachedAttachment.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaExternalKeyAttachedAttachment = BetaExternalKeyAttachedAttachment.builder().build()

        val roundtrippedBetaExternalKeyAttachedAttachment =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaExternalKeyAttachedAttachment),
                jacksonTypeRef<BetaExternalKeyAttachedAttachment>(),
            )

        assertThat(roundtrippedBetaExternalKeyAttachedAttachment)
            .isEqualTo(betaExternalKeyAttachedAttachment)
    }
}
