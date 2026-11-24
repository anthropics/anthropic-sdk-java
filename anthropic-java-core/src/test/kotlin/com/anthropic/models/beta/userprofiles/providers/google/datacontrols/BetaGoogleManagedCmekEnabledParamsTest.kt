package com.anthropic.models.beta.userprofiles.providers.google.datacontrols

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedCmekEnabledParamsTest {

    @Test
    fun create() {
        val betaGoogleManagedCmekEnabledParams =
            BetaGoogleManagedCmekEnabledParams.builder()
                .externalKeyIds(
                    BetaGoogleManagedCmekEnabledParams.ExternalKeyIds.builder()
                        .putAdditionalProperty("foo", JsonValue.from("string"))
                        .build()
                )
                .build()

        assertThat(betaGoogleManagedCmekEnabledParams.externalKeyIds())
            .contains(
                BetaGoogleManagedCmekEnabledParams.ExternalKeyIds.builder()
                    .putAdditionalProperty("foo", JsonValue.from("string"))
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedCmekEnabledParams =
            BetaGoogleManagedCmekEnabledParams.builder()
                .externalKeyIds(
                    BetaGoogleManagedCmekEnabledParams.ExternalKeyIds.builder()
                        .putAdditionalProperty("foo", JsonValue.from("string"))
                        .build()
                )
                .build()

        val roundtrippedBetaGoogleManagedCmekEnabledParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedCmekEnabledParams),
                jacksonTypeRef<BetaGoogleManagedCmekEnabledParams>(),
            )

        assertThat(roundtrippedBetaGoogleManagedCmekEnabledParams)
            .isEqualTo(betaGoogleManagedCmekEnabledParams)
    }
}
