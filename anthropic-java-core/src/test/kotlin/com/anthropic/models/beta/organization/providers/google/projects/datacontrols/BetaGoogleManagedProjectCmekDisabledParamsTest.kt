package com.anthropic.models.beta.organization.providers.google.projects.datacontrols

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedProjectCmekDisabledParamsTest {

    @Test
    fun create() {
        val betaGoogleManagedProjectCmekDisabledParams =
            BetaGoogleManagedProjectCmekDisabledParams.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedProjectCmekDisabledParams =
            BetaGoogleManagedProjectCmekDisabledParams.builder().build()

        val roundtrippedBetaGoogleManagedProjectCmekDisabledParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedProjectCmekDisabledParams),
                jacksonTypeRef<BetaGoogleManagedProjectCmekDisabledParams>(),
            )

        assertThat(roundtrippedBetaGoogleManagedProjectCmekDisabledParams)
            .isEqualTo(betaGoogleManagedProjectCmekDisabledParams)
    }
}
