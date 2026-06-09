// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaManagedAgentsCredentialNetworkingParamsTest {

    @Test
    fun ofUnrestricted() {
        val unrestricted =
            BetaManagedAgentsUnrestrictedCredentialNetworkingParams.builder()
                .type(BetaManagedAgentsUnrestrictedCredentialNetworkingParams.Type.UNRESTRICTED)
                .build()

        val betaManagedAgentsCredentialNetworkingParams =
            BetaManagedAgentsCredentialNetworkingParams.ofUnrestricted(unrestricted)

        assertThat(betaManagedAgentsCredentialNetworkingParams.unrestricted())
            .contains(unrestricted)
        assertThat(betaManagedAgentsCredentialNetworkingParams.limited()).isEmpty
    }

    @Test
    fun ofUnrestrictedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsCredentialNetworkingParams =
            BetaManagedAgentsCredentialNetworkingParams.ofUnrestricted(
                BetaManagedAgentsUnrestrictedCredentialNetworkingParams.builder()
                    .type(BetaManagedAgentsUnrestrictedCredentialNetworkingParams.Type.UNRESTRICTED)
                    .build()
            )

        val roundtrippedBetaManagedAgentsCredentialNetworkingParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsCredentialNetworkingParams),
                jacksonTypeRef<BetaManagedAgentsCredentialNetworkingParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsCredentialNetworkingParams)
            .isEqualTo(betaManagedAgentsCredentialNetworkingParams)
    }

    @Test
    fun ofLimited() {
        val limited =
            BetaManagedAgentsLimitedCredentialNetworkingParams.builder()
                .addAllowedHost("string")
                .type(BetaManagedAgentsLimitedCredentialNetworkingParams.Type.LIMITED)
                .build()

        val betaManagedAgentsCredentialNetworkingParams =
            BetaManagedAgentsCredentialNetworkingParams.ofLimited(limited)

        assertThat(betaManagedAgentsCredentialNetworkingParams.unrestricted()).isEmpty
        assertThat(betaManagedAgentsCredentialNetworkingParams.limited()).contains(limited)
    }

    @Test
    fun ofLimitedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsCredentialNetworkingParams =
            BetaManagedAgentsCredentialNetworkingParams.ofLimited(
                BetaManagedAgentsLimitedCredentialNetworkingParams.builder()
                    .addAllowedHost("string")
                    .type(BetaManagedAgentsLimitedCredentialNetworkingParams.Type.LIMITED)
                    .build()
            )

        val roundtrippedBetaManagedAgentsCredentialNetworkingParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsCredentialNetworkingParams),
                jacksonTypeRef<BetaManagedAgentsCredentialNetworkingParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsCredentialNetworkingParams)
            .isEqualTo(betaManagedAgentsCredentialNetworkingParams)
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
        val betaManagedAgentsCredentialNetworkingParams =
            jsonMapper()
                .convertValue(
                    testCase.value,
                    jacksonTypeRef<BetaManagedAgentsCredentialNetworkingParams>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaManagedAgentsCredentialNetworkingParams.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
