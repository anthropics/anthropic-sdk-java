// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.externalkeys

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaExternalKeyUnattachedAttachmentTest {

    @Test
    fun create() {
        val betaExternalKeyUnattachedAttachment =
            BetaExternalKeyUnattachedAttachment.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaExternalKeyUnattachedAttachment =
            BetaExternalKeyUnattachedAttachment.builder().build()

        val roundtrippedBetaExternalKeyUnattachedAttachment =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaExternalKeyUnattachedAttachment),
                jacksonTypeRef<BetaExternalKeyUnattachedAttachment>(),
            )

        assertThat(roundtrippedBetaExternalKeyUnattachedAttachment)
            .isEqualTo(betaExternalKeyUnattachedAttachment)
    }
}
