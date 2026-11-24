package com.anthropic.models.beta.userprofiles.providers.google.datacontrols

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedCmekEnabledTest {

    @Test
    fun create() {
        val betaGoogleManagedCmekEnabled =
            BetaGoogleManagedCmekEnabled.builder()
                .externalKeyIds(
                    BetaGoogleManagedCmekEnabled.ExternalKeyIds.builder()
                        .putAdditionalProperty("foo", JsonValue.from("string"))
                        .build()
                )
                .addRetiredKey(
                    BetaGoogleManagedRetiredKey.builder()
                        .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .externalKeyId("external_key_id")
                        .region("region")
                        .build()
                )
                .status(
                    BetaGoogleManagedCmekEnabled.Status.builder()
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

        assertThat(betaGoogleManagedCmekEnabled.externalKeyIds())
            .isEqualTo(
                BetaGoogleManagedCmekEnabled.ExternalKeyIds.builder()
                    .putAdditionalProperty("foo", JsonValue.from("string"))
                    .build()
            )
        assertThat(betaGoogleManagedCmekEnabled.retiredKeys())
            .containsExactly(
                BetaGoogleManagedRetiredKey.builder()
                    .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .externalKeyId("external_key_id")
                    .region("region")
                    .build()
            )
        assertThat(betaGoogleManagedCmekEnabled.status())
            .isEqualTo(
                BetaGoogleManagedCmekEnabled.Status.builder()
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
        val betaGoogleManagedCmekEnabled =
            BetaGoogleManagedCmekEnabled.builder()
                .externalKeyIds(
                    BetaGoogleManagedCmekEnabled.ExternalKeyIds.builder()
                        .putAdditionalProperty("foo", JsonValue.from("string"))
                        .build()
                )
                .addRetiredKey(
                    BetaGoogleManagedRetiredKey.builder()
                        .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .externalKeyId("external_key_id")
                        .region("region")
                        .build()
                )
                .status(
                    BetaGoogleManagedCmekEnabled.Status.builder()
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

        val roundtrippedBetaGoogleManagedCmekEnabled =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedCmekEnabled),
                jacksonTypeRef<BetaGoogleManagedCmekEnabled>(),
            )

        assertThat(roundtrippedBetaGoogleManagedCmekEnabled).isEqualTo(betaGoogleManagedCmekEnabled)
    }
}
