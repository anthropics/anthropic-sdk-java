package com.anthropic.models.beta.organization.claudetag

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComplianceConfigurationRestrictedErrorDetailsTest {

    @Test
    fun create() {
        val betaComplianceConfigurationRestrictedErrorDetails =
            BetaComplianceConfigurationRestrictedErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComplianceConfigurationRestrictedErrorDetails =
            BetaComplianceConfigurationRestrictedErrorDetails.builder().build()

        val roundtrippedBetaComplianceConfigurationRestrictedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComplianceConfigurationRestrictedErrorDetails),
                jacksonTypeRef<BetaComplianceConfigurationRestrictedErrorDetails>(),
            )

        assertThat(roundtrippedBetaComplianceConfigurationRestrictedErrorDetails)
            .isEqualTo(betaComplianceConfigurationRestrictedErrorDetails)
    }
}
