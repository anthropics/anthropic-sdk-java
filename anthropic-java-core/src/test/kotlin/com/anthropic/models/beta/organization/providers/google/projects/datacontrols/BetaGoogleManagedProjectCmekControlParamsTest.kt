package com.anthropic.models.beta.organization.providers.google.projects.datacontrols

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaGoogleManagedProjectCmekControlParamsTest {

    @Test
    fun ofDisabled() {
        val disabled = BetaGoogleManagedProjectCmekDisabledParams.builder().build()

        val betaGoogleManagedProjectCmekControlParams =
            BetaGoogleManagedProjectCmekControlParams.ofDisabled(disabled)

        assertThat(betaGoogleManagedProjectCmekControlParams.disabled()).contains(disabled)
        assertThat(betaGoogleManagedProjectCmekControlParams.enabled()).isEmpty
    }

    @Test
    fun ofDisabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedProjectCmekControlParams =
            BetaGoogleManagedProjectCmekControlParams.ofDisabled(
                BetaGoogleManagedProjectCmekDisabledParams.builder().build()
            )

        val roundtrippedBetaGoogleManagedProjectCmekControlParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedProjectCmekControlParams),
                jacksonTypeRef<BetaGoogleManagedProjectCmekControlParams>(),
            )

        assertThat(roundtrippedBetaGoogleManagedProjectCmekControlParams)
            .isEqualTo(betaGoogleManagedProjectCmekControlParams)
    }

    @Test
    fun ofEnabled() {
        val enabled =
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

        val betaGoogleManagedProjectCmekControlParams =
            BetaGoogleManagedProjectCmekControlParams.ofEnabled(enabled)

        assertThat(betaGoogleManagedProjectCmekControlParams.disabled()).isEmpty
        assertThat(betaGoogleManagedProjectCmekControlParams.enabled()).contains(enabled)
    }

    @Test
    fun ofEnabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedProjectCmekControlParams =
            BetaGoogleManagedProjectCmekControlParams.ofEnabled(
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
            )

        val roundtrippedBetaGoogleManagedProjectCmekControlParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedProjectCmekControlParams),
                jacksonTypeRef<BetaGoogleManagedProjectCmekControlParams>(),
            )

        assertThat(roundtrippedBetaGoogleManagedProjectCmekControlParams)
            .isEqualTo(betaGoogleManagedProjectCmekControlParams)
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
        val betaGoogleManagedProjectCmekControlParams =
            jsonMapper()
                .convertValue(
                    testCase.value,
                    jacksonTypeRef<BetaGoogleManagedProjectCmekControlParams>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaGoogleManagedProjectCmekControlParams.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
