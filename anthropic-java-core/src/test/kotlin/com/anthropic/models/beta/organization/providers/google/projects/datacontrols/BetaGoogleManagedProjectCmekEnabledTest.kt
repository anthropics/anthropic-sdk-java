package com.anthropic.models.beta.organization.providers.google.projects.datacontrols

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedProjectCmekEnabledTest {

    @Test
    fun create() {
        val betaGoogleManagedProjectCmekEnabled =
            BetaGoogleManagedProjectCmekEnabled.builder()
                .externalKeyIds(
                    BetaGoogleManagedProjectCmekEnabled.ExternalKeyIds.builder()
                        .putAdditionalProperty(
                            "us",
                            JsonValue.from("ekey_01SDCCSbTxrXDpWc1phhtcfK"),
                        )
                        .build()
                )
                .addRetiredKey(
                    BetaGoogleManagedProjectRetiredKey.builder()
                        .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .externalKeyId("ekey_01SDCCSbTxrXDpWc1phhtcfK")
                        .region("us")
                        .build()
                )
                .status(
                    BetaGoogleManagedProjectCmekEnabled.Status.builder()
                        .putAdditionalProperty(
                            "foo",
                            JsonValue.from(
                                mapOf(
                                    "effective_external_key_id" to "effective_external_key_id",
                                    "type" to "not_configured",
                                )
                            ),
                        )
                        .build()
                )
                .build()

        assertThat(betaGoogleManagedProjectCmekEnabled.externalKeyIds())
            .isEqualTo(
                BetaGoogleManagedProjectCmekEnabled.ExternalKeyIds.builder()
                    .putAdditionalProperty("us", JsonValue.from("ekey_01SDCCSbTxrXDpWc1phhtcfK"))
                    .build()
            )
        assertThat(betaGoogleManagedProjectCmekEnabled.retiredKeys())
            .containsExactly(
                BetaGoogleManagedProjectRetiredKey.builder()
                    .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .externalKeyId("ekey_01SDCCSbTxrXDpWc1phhtcfK")
                    .region("us")
                    .build()
            )
        assertThat(betaGoogleManagedProjectCmekEnabled.status())
            .isEqualTo(
                BetaGoogleManagedProjectCmekEnabled.Status.builder()
                    .putAdditionalProperty(
                        "foo",
                        JsonValue.from(
                            mapOf(
                                "effective_external_key_id" to "effective_external_key_id",
                                "type" to "not_configured",
                            )
                        ),
                    )
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedProjectCmekEnabled =
            BetaGoogleManagedProjectCmekEnabled.builder()
                .externalKeyIds(
                    BetaGoogleManagedProjectCmekEnabled.ExternalKeyIds.builder()
                        .putAdditionalProperty(
                            "us",
                            JsonValue.from("ekey_01SDCCSbTxrXDpWc1phhtcfK"),
                        )
                        .build()
                )
                .addRetiredKey(
                    BetaGoogleManagedProjectRetiredKey.builder()
                        .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .externalKeyId("ekey_01SDCCSbTxrXDpWc1phhtcfK")
                        .region("us")
                        .build()
                )
                .status(
                    BetaGoogleManagedProjectCmekEnabled.Status.builder()
                        .putAdditionalProperty(
                            "foo",
                            JsonValue.from(
                                mapOf(
                                    "effective_external_key_id" to "effective_external_key_id",
                                    "type" to "not_configured",
                                )
                            ),
                        )
                        .build()
                )
                .build()

        val roundtrippedBetaGoogleManagedProjectCmekEnabled =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedProjectCmekEnabled),
                jacksonTypeRef<BetaGoogleManagedProjectCmekEnabled>(),
            )

        assertThat(roundtrippedBetaGoogleManagedProjectCmekEnabled)
            .isEqualTo(betaGoogleManagedProjectCmekEnabled)
    }
}
