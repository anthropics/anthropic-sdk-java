package com.anthropic.models.beta.organization.providers.google.projects.datacontrols

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedProjectStorageDisabledParamsTest {

    @Test
    fun create() {
        val betaGoogleManagedProjectStorageDisabledParams =
            BetaGoogleManagedProjectStorageDisabledParams.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedProjectStorageDisabledParams =
            BetaGoogleManagedProjectStorageDisabledParams.builder().build()

        val roundtrippedBetaGoogleManagedProjectStorageDisabledParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedProjectStorageDisabledParams),
                jacksonTypeRef<BetaGoogleManagedProjectStorageDisabledParams>(),
            )

        assertThat(roundtrippedBetaGoogleManagedProjectStorageDisabledParams)
            .isEqualTo(betaGoogleManagedProjectStorageDisabledParams)
    }
}
