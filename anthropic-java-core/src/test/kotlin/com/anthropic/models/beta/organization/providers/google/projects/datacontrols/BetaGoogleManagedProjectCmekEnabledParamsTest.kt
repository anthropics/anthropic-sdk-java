package com.anthropic.models.beta.organization.providers.google.projects.datacontrols

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedProjectCmekEnabledParamsTest {

    @Test
    fun create() {
        val betaGoogleManagedProjectCmekEnabledParams =
            BetaGoogleManagedProjectCmekEnabledParams.builder()
                .externalKeyIds(
                    BetaGoogleManagedProjectCmekEnabledParams.ExternalKeyIds.builder()
                        .putAdditionalProperty(
                            "us",
                            JsonValue.from("ekey_01SDCCSbTxrXDpWc1phhtcfK"),
                        )
                        .build()
                )
                .build()

        assertThat(betaGoogleManagedProjectCmekEnabledParams.externalKeyIds())
            .contains(
                BetaGoogleManagedProjectCmekEnabledParams.ExternalKeyIds.builder()
                    .putAdditionalProperty("us", JsonValue.from("ekey_01SDCCSbTxrXDpWc1phhtcfK"))
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedProjectCmekEnabledParams =
            BetaGoogleManagedProjectCmekEnabledParams.builder()
                .externalKeyIds(
                    BetaGoogleManagedProjectCmekEnabledParams.ExternalKeyIds.builder()
                        .putAdditionalProperty(
                            "us",
                            JsonValue.from("ekey_01SDCCSbTxrXDpWc1phhtcfK"),
                        )
                        .build()
                )
                .build()

        val roundtrippedBetaGoogleManagedProjectCmekEnabledParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedProjectCmekEnabledParams),
                jacksonTypeRef<BetaGoogleManagedProjectCmekEnabledParams>(),
            )

        assertThat(roundtrippedBetaGoogleManagedProjectCmekEnabledParams)
            .isEqualTo(betaGoogleManagedProjectCmekEnabledParams)
    }
}
