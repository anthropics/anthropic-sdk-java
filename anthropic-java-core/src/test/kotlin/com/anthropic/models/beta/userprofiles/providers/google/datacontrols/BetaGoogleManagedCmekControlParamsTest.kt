package com.anthropic.models.beta.userprofiles.providers.google.datacontrols

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Optional
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaGoogleManagedCmekControlParamsTest {

    @Test
    fun ofDisabled() {
        val disabled = BetaGoogleManagedCmekDisabledParams.builder().build()

        val betaGoogleManagedCmekControlParams =
            BetaGoogleManagedCmekControlParams.ofDisabled(disabled)

        assertThat(presentVariants(betaGoogleManagedCmekControlParams))
            .isEqualTo(mapOf("disabled" to Optional.of(disabled)))
    }

    @Test
    fun ofDisabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedCmekControlParams =
            BetaGoogleManagedCmekControlParams.ofDisabled(
                BetaGoogleManagedCmekDisabledParams.builder().build()
            )

        val roundtrippedBetaGoogleManagedCmekControlParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedCmekControlParams),
                jacksonTypeRef<BetaGoogleManagedCmekControlParams>(),
            )

        assertThat(roundtrippedBetaGoogleManagedCmekControlParams)
            .isEqualTo(betaGoogleManagedCmekControlParams)
    }

    @Test
    fun ofEnabled() {
        val enabled =
            BetaGoogleManagedCmekEnabledParams.builder()
                .externalKeyIds(
                    BetaGoogleManagedCmekEnabledParams.ExternalKeyIds.builder()
                        .putAdditionalProperty("foo", JsonValue.from("string"))
                        .build()
                )
                .build()

        val betaGoogleManagedCmekControlParams =
            BetaGoogleManagedCmekControlParams.ofEnabled(enabled)

        assertThat(presentVariants(betaGoogleManagedCmekControlParams))
            .isEqualTo(mapOf("enabled" to Optional.of(enabled)))
    }

    @Test
    fun ofEnabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedCmekControlParams =
            BetaGoogleManagedCmekControlParams.ofEnabled(
                BetaGoogleManagedCmekEnabledParams.builder()
                    .externalKeyIds(
                        BetaGoogleManagedCmekEnabledParams.ExternalKeyIds.builder()
                            .putAdditionalProperty("foo", JsonValue.from("string"))
                            .build()
                    )
                    .build()
            )

        val roundtrippedBetaGoogleManagedCmekControlParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedCmekControlParams),
                jacksonTypeRef<BetaGoogleManagedCmekControlParams>(),
            )

        assertThat(roundtrippedBetaGoogleManagedCmekControlParams)
            .isEqualTo(betaGoogleManagedCmekControlParams)
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
        val betaGoogleManagedCmekControlParams =
            jsonMapper()
                .convertValue(testCase.value, jacksonTypeRef<BetaGoogleManagedCmekControlParams>())

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaGoogleManagedCmekControlParams.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }

    private fun presentVariants(
        betaGoogleManagedCmekControlParams: BetaGoogleManagedCmekControlParams
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "disabled" to betaGoogleManagedCmekControlParams.disabled(),
                "enabled" to betaGoogleManagedCmekControlParams.enabled(),
            )
            .filterValues { it.isPresent }
}
