package com.anthropic.models.beta.organization.compliancesettings

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAccessTransparencyTest {

    @Test
    fun create() {
        val betaAccessTransparency = BetaAccessTransparency.of(BetaAccessTransparency.Type.DISABLED)

        assertThat(betaAccessTransparency.type()).isEqualTo(BetaAccessTransparency.Type.DISABLED)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAccessTransparency = BetaAccessTransparency.of(BetaAccessTransparency.Type.DISABLED)

        val roundtrippedBetaAccessTransparency =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAccessTransparency),
                jacksonTypeRef<BetaAccessTransparency>(),
            )

        assertThat(roundtrippedBetaAccessTransparency).isEqualTo(betaAccessTransparency)
    }
}
