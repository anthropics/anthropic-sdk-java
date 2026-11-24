package com.anthropic.models.beta.userprofiles.providers.google.datacontrols

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import java.util.Optional
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaGoogleManagedCmekControlTest {

    @Test
    fun ofDisabled() {
        val disabled =
            BetaGoogleManagedCmekDisabled.builder()
                .addRetiredKey(
                    BetaGoogleManagedRetiredKey.builder()
                        .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .externalKeyId("external_key_id")
                        .region("region")
                        .build()
                )
                .build()

        val betaGoogleManagedCmekControl = BetaGoogleManagedCmekControl.ofDisabled(disabled)

        assertThat(presentVariants(betaGoogleManagedCmekControl))
            .isEqualTo(mapOf("disabled" to Optional.of(disabled)))
    }

    @Test
    fun ofDisabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedCmekControl =
            BetaGoogleManagedCmekControl.ofDisabled(
                BetaGoogleManagedCmekDisabled.builder()
                    .addRetiredKey(
                        BetaGoogleManagedRetiredKey.builder()
                            .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                            .externalKeyId("external_key_id")
                            .region("region")
                            .build()
                    )
                    .build()
            )

        val roundtrippedBetaGoogleManagedCmekControl =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedCmekControl),
                jacksonTypeRef<BetaGoogleManagedCmekControl>(),
            )

        assertThat(roundtrippedBetaGoogleManagedCmekControl).isEqualTo(betaGoogleManagedCmekControl)
    }

    @Test
    fun ofEnabled() {
        val enabled =
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

        val betaGoogleManagedCmekControl = BetaGoogleManagedCmekControl.ofEnabled(enabled)

        assertThat(presentVariants(betaGoogleManagedCmekControl))
            .isEqualTo(mapOf("enabled" to Optional.of(enabled)))
    }

    @Test
    fun ofEnabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedCmekControl =
            BetaGoogleManagedCmekControl.ofEnabled(
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
            )

        val roundtrippedBetaGoogleManagedCmekControl =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedCmekControl),
                jacksonTypeRef<BetaGoogleManagedCmekControl>(),
            )

        assertThat(roundtrippedBetaGoogleManagedCmekControl).isEqualTo(betaGoogleManagedCmekControl)
    }

    @Test
    fun unknownVariantCommonProperties() {
        val betaGoogleManagedCmekControl =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "type" to "unknown_variant",
                            "retired_keys" to
                                listOf(
                                    mapOf(
                                        "expires_at" to "2019-12-27T18:11:19.117Z",
                                        "external_key_id" to "external_key_id",
                                        "region" to "region",
                                    )
                                ),
                        )
                    ),
                    jacksonTypeRef<BetaGoogleManagedCmekControl>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> { betaGoogleManagedCmekControl.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaGoogleManagedCmekControl.retiredKeys())
            .containsExactly(
                BetaGoogleManagedRetiredKey.builder()
                    .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .externalKeyId("external_key_id")
                    .region("region")
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
        val betaGoogleManagedCmekControl =
            jsonMapper()
                .convertValue(testCase.value, jacksonTypeRef<BetaGoogleManagedCmekControl>())

        val e =
            assertThrows<AnthropicInvalidDataException> { betaGoogleManagedCmekControl.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThrows<AnthropicInvalidDataException> { betaGoogleManagedCmekControl.retiredKeys() }
    }

    private fun presentVariants(
        betaGoogleManagedCmekControl: BetaGoogleManagedCmekControl
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "disabled" to betaGoogleManagedCmekControl.disabled(),
                "enabled" to betaGoogleManagedCmekControl.enabled(),
            )
            .filterValues { it.isPresent }
}
