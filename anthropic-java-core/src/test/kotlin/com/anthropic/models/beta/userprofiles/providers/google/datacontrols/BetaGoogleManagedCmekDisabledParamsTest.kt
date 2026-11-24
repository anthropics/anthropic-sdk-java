package com.anthropic.models.beta.userprofiles.providers.google.datacontrols

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedCmekDisabledParamsTest {

    @Test
    fun create() {
        val betaGoogleManagedCmekDisabledParams =
            BetaGoogleManagedCmekDisabledParams.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedCmekDisabledParams =
            BetaGoogleManagedCmekDisabledParams.builder().build()

        val roundtrippedBetaGoogleManagedCmekDisabledParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedCmekDisabledParams),
                jacksonTypeRef<BetaGoogleManagedCmekDisabledParams>(),
            )

        assertThat(roundtrippedBetaGoogleManagedCmekDisabledParams)
            .isEqualTo(betaGoogleManagedCmekDisabledParams)
    }
}
