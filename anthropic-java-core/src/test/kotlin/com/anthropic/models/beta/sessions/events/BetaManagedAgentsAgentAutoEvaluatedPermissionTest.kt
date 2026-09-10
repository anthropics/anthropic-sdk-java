package com.anthropic.models.beta.sessions.events

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaManagedAgentsAgentAutoEvaluatedPermissionTest {

    @Test
    fun ofAllow() {
        val allow = BetaManagedAgentsAgentAutoEvaluatedPermissionAllow.builder().build()

        val betaManagedAgentsAgentAutoEvaluatedPermission =
            BetaManagedAgentsAgentAutoEvaluatedPermission.ofAllow(allow)

        assertThat(betaManagedAgentsAgentAutoEvaluatedPermission.allow()).contains(allow)
        assertThat(betaManagedAgentsAgentAutoEvaluatedPermission.ask()).isEmpty
        assertThat(betaManagedAgentsAgentAutoEvaluatedPermission.deny()).isEmpty
    }

    @Test
    fun ofAllowRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentAutoEvaluatedPermission =
            BetaManagedAgentsAgentAutoEvaluatedPermission.ofAllow(
                BetaManagedAgentsAgentAutoEvaluatedPermissionAllow.builder().build()
            )

        val roundtrippedBetaManagedAgentsAgentAutoEvaluatedPermission =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentAutoEvaluatedPermission),
                jacksonTypeRef<BetaManagedAgentsAgentAutoEvaluatedPermission>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentAutoEvaluatedPermission)
            .isEqualTo(betaManagedAgentsAgentAutoEvaluatedPermission)
    }

    @Test
    fun ofAsk() {
        val ask = BetaManagedAgentsAgentAutoEvaluatedPermissionAsk.of("reason_code")

        val betaManagedAgentsAgentAutoEvaluatedPermission =
            BetaManagedAgentsAgentAutoEvaluatedPermission.ofAsk(ask)

        assertThat(betaManagedAgentsAgentAutoEvaluatedPermission.allow()).isEmpty
        assertThat(betaManagedAgentsAgentAutoEvaluatedPermission.ask()).contains(ask)
        assertThat(betaManagedAgentsAgentAutoEvaluatedPermission.deny()).isEmpty
    }

    @Test
    fun ofAskRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentAutoEvaluatedPermission =
            BetaManagedAgentsAgentAutoEvaluatedPermission.ofAsk(
                BetaManagedAgentsAgentAutoEvaluatedPermissionAsk.of("reason_code")
            )

        val roundtrippedBetaManagedAgentsAgentAutoEvaluatedPermission =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentAutoEvaluatedPermission),
                jacksonTypeRef<BetaManagedAgentsAgentAutoEvaluatedPermission>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentAutoEvaluatedPermission)
            .isEqualTo(betaManagedAgentsAgentAutoEvaluatedPermission)
    }

    @Test
    fun ofDeny() {
        val deny = BetaManagedAgentsAgentAutoEvaluatedPermissionDeny.of("reason_code")

        val betaManagedAgentsAgentAutoEvaluatedPermission =
            BetaManagedAgentsAgentAutoEvaluatedPermission.ofDeny(deny)

        assertThat(betaManagedAgentsAgentAutoEvaluatedPermission.allow()).isEmpty
        assertThat(betaManagedAgentsAgentAutoEvaluatedPermission.ask()).isEmpty
        assertThat(betaManagedAgentsAgentAutoEvaluatedPermission.deny()).contains(deny)
    }

    @Test
    fun ofDenyRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentAutoEvaluatedPermission =
            BetaManagedAgentsAgentAutoEvaluatedPermission.ofDeny(
                BetaManagedAgentsAgentAutoEvaluatedPermissionDeny.of("reason_code")
            )

        val roundtrippedBetaManagedAgentsAgentAutoEvaluatedPermission =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentAutoEvaluatedPermission),
                jacksonTypeRef<BetaManagedAgentsAgentAutoEvaluatedPermission>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentAutoEvaluatedPermission)
            .isEqualTo(betaManagedAgentsAgentAutoEvaluatedPermission)
    }

    @Test
    fun unknownVariantCommonProperties() {
        val betaManagedAgentsAgentAutoEvaluatedPermission =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf("type" to "unknown_variant", "reason_code" to "reason_code")
                    ),
                    jacksonTypeRef<BetaManagedAgentsAgentAutoEvaluatedPermission>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaManagedAgentsAgentAutoEvaluatedPermission.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaManagedAgentsAgentAutoEvaluatedPermission.reasonCode())
            .contains("reason_code")

        val mismatchedBetaManagedAgentsAgentAutoEvaluatedPermission =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf("type" to "unknown_variant", "reason_code" to listOf("invalid"))
                    ),
                    jacksonTypeRef<BetaManagedAgentsAgentAutoEvaluatedPermission>(),
                )

        assertThat(mismatchedBetaManagedAgentsAgentAutoEvaluatedPermission.reasonCode()).isEmpty
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
        val betaManagedAgentsAgentAutoEvaluatedPermission =
            jsonMapper()
                .convertValue(
                    testCase.value,
                    jacksonTypeRef<BetaManagedAgentsAgentAutoEvaluatedPermission>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaManagedAgentsAgentAutoEvaluatedPermission.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaManagedAgentsAgentAutoEvaluatedPermission.reasonCode()).isEmpty
    }
}
