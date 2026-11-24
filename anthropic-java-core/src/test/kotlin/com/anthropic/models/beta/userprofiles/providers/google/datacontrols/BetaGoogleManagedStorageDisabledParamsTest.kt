package com.anthropic.models.beta.userprofiles.providers.google.datacontrols

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedStorageDisabledParamsTest {

    @Test
    fun create() {
        val betaGoogleManagedStorageDisabledParams =
            BetaGoogleManagedStorageDisabledParams.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedStorageDisabledParams =
            BetaGoogleManagedStorageDisabledParams.builder().build()

        val roundtrippedBetaGoogleManagedStorageDisabledParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedStorageDisabledParams),
                jacksonTypeRef<BetaGoogleManagedStorageDisabledParams>(),
            )

        assertThat(roundtrippedBetaGoogleManagedStorageDisabledParams)
            .isEqualTo(betaGoogleManagedStorageDisabledParams)
    }
}
