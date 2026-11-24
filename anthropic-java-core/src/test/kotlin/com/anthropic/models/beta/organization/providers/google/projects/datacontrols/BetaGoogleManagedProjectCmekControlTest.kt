package com.anthropic.models.beta.organization.providers.google.projects.datacontrols

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaGoogleManagedProjectCmekControlTest {

    @Test
    fun ofDisabled() {
        val disabled =
            BetaGoogleManagedProjectCmekDisabled.builder()
                .addRetiredKey(
                    BetaGoogleManagedProjectRetiredKey.builder()
                        .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .externalKeyId("ekey_01SDCCSbTxrXDpWc1phhtcfK")
                        .region("us")
                        .build()
                )
                .build()

        val betaGoogleManagedProjectCmekControl =
            BetaGoogleManagedProjectCmekControl.ofDisabled(disabled)

        assertThat(betaGoogleManagedProjectCmekControl.disabled()).contains(disabled)
        assertThat(betaGoogleManagedProjectCmekControl.enabled()).isEmpty
    }

    @Test
    fun ofDisabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedProjectCmekControl =
            BetaGoogleManagedProjectCmekControl.ofDisabled(
                BetaGoogleManagedProjectCmekDisabled.builder()
                    .addRetiredKey(
                        BetaGoogleManagedProjectRetiredKey.builder()
                            .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                            .externalKeyId("ekey_01SDCCSbTxrXDpWc1phhtcfK")
                            .region("us")
                            .build()
                    )
                    .build()
            )

        val roundtrippedBetaGoogleManagedProjectCmekControl =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedProjectCmekControl),
                jacksonTypeRef<BetaGoogleManagedProjectCmekControl>(),
            )

        assertThat(roundtrippedBetaGoogleManagedProjectCmekControl)
            .isEqualTo(betaGoogleManagedProjectCmekControl)
    }

    @Test
    fun ofEnabled() {
        val enabled =
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

        val betaGoogleManagedProjectCmekControl =
            BetaGoogleManagedProjectCmekControl.ofEnabled(enabled)

        assertThat(betaGoogleManagedProjectCmekControl.disabled()).isEmpty
        assertThat(betaGoogleManagedProjectCmekControl.enabled()).contains(enabled)
    }

    @Test
    fun ofEnabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedProjectCmekControl =
            BetaGoogleManagedProjectCmekControl.ofEnabled(
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
            )

        val roundtrippedBetaGoogleManagedProjectCmekControl =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedProjectCmekControl),
                jacksonTypeRef<BetaGoogleManagedProjectCmekControl>(),
            )

        assertThat(roundtrippedBetaGoogleManagedProjectCmekControl)
            .isEqualTo(betaGoogleManagedProjectCmekControl)
    }

    @Test
    fun unknownVariantCommonProperties() {
        val betaGoogleManagedProjectCmekControl =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "type" to "unknown_variant",
                            "retired_keys" to
                                listOf(
                                    mapOf(
                                        "expires_at" to "2019-12-27T18:11:19.117Z",
                                        "external_key_id" to "ekey_01SDCCSbTxrXDpWc1phhtcfK",
                                        "region" to "us",
                                    )
                                ),
                        )
                    ),
                    jacksonTypeRef<BetaGoogleManagedProjectCmekControl>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaGoogleManagedProjectCmekControl.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaGoogleManagedProjectCmekControl.retiredKeys())
            .containsExactly(
                BetaGoogleManagedProjectRetiredKey.builder()
                    .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .externalKeyId("ekey_01SDCCSbTxrXDpWc1phhtcfK")
                    .region("us")
                    .build()
            )
    }

    enum class IncompatibleJsonShapeTestCase(val value: JsonValue) {
        BOOLEAN(JsonValue.from(false)),
        STRING(JsonValue.from("invalid")),
        INTEGER(JsonValue.from(-1)),
        FLOAT(JsonValue.from(3.14)),
        ARRAY(JsonValue.from(listOf("invalid", "array"))),
    }

    @ParameterizedTest
    @EnumSource
    fun incompatibleJsonShapeDeserializesToUnknown(testCase: IncompatibleJsonShapeTestCase) {
        val betaGoogleManagedProjectCmekControl =
            jsonMapper()
                .convertValue(testCase.value, jacksonTypeRef<BetaGoogleManagedProjectCmekControl>())

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaGoogleManagedProjectCmekControl.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThrows<AnthropicInvalidDataException> {
            betaGoogleManagedProjectCmekControl.retiredKeys()
        }
    }
}
