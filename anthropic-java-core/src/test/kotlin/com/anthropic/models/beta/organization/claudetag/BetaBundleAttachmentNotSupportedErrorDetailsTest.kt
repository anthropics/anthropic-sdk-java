package com.anthropic.models.beta.organization.claudetag

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBundleAttachmentNotSupportedErrorDetailsTest {

    @Test
    fun create() {
        val betaBundleAttachmentNotSupportedErrorDetails =
            BetaBundleAttachmentNotSupportedErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBundleAttachmentNotSupportedErrorDetails =
            BetaBundleAttachmentNotSupportedErrorDetails.builder().build()

        val roundtrippedBetaBundleAttachmentNotSupportedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBundleAttachmentNotSupportedErrorDetails),
                jacksonTypeRef<BetaBundleAttachmentNotSupportedErrorDetails>(),
            )

        assertThat(roundtrippedBetaBundleAttachmentNotSupportedErrorDetails)
            .isEqualTo(betaBundleAttachmentNotSupportedErrorDetails)
    }
}
