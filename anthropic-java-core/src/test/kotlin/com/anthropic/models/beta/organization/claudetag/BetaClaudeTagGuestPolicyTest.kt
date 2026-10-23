package com.anthropic.models.beta.organization.claudetag

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaClaudeTagGuestPolicyTest {

    @Test
    fun ofAllow() {
        val allow = BetaClaudeTagGuestPolicyAllow.builder().build()

        val betaClaudeTagGuestPolicy = BetaClaudeTagGuestPolicy.ofAllow(allow)

        assertThat(betaClaudeTagGuestPolicy.allow()).contains(allow)
        assertThat(betaClaudeTagGuestPolicy.restrict()).isEmpty
        assertThat(betaClaudeTagGuestPolicy.channelOnly()).isEmpty
    }

    @Test
    fun ofAllowRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeTagGuestPolicy =
            BetaClaudeTagGuestPolicy.ofAllow(BetaClaudeTagGuestPolicyAllow.builder().build())

        val roundtrippedBetaClaudeTagGuestPolicy =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeTagGuestPolicy),
                jacksonTypeRef<BetaClaudeTagGuestPolicy>(),
            )

        assertThat(roundtrippedBetaClaudeTagGuestPolicy).isEqualTo(betaClaudeTagGuestPolicy)
    }

    @Test
    fun ofRestrict() {
        val restrict = BetaClaudeTagGuestPolicyRestrict.builder().build()

        val betaClaudeTagGuestPolicy = BetaClaudeTagGuestPolicy.ofRestrict(restrict)

        assertThat(betaClaudeTagGuestPolicy.allow()).isEmpty
        assertThat(betaClaudeTagGuestPolicy.restrict()).contains(restrict)
        assertThat(betaClaudeTagGuestPolicy.channelOnly()).isEmpty
    }

    @Test
    fun ofRestrictRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeTagGuestPolicy =
            BetaClaudeTagGuestPolicy.ofRestrict(BetaClaudeTagGuestPolicyRestrict.builder().build())

        val roundtrippedBetaClaudeTagGuestPolicy =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeTagGuestPolicy),
                jacksonTypeRef<BetaClaudeTagGuestPolicy>(),
            )

        assertThat(roundtrippedBetaClaudeTagGuestPolicy).isEqualTo(betaClaudeTagGuestPolicy)
    }

    @Test
    fun ofChannelOnly() {
        val channelOnly = BetaClaudeTagGuestPolicyChannelOnly.builder().build()

        val betaClaudeTagGuestPolicy = BetaClaudeTagGuestPolicy.ofChannelOnly(channelOnly)

        assertThat(betaClaudeTagGuestPolicy.allow()).isEmpty
        assertThat(betaClaudeTagGuestPolicy.restrict()).isEmpty
        assertThat(betaClaudeTagGuestPolicy.channelOnly()).contains(channelOnly)
    }

    @Test
    fun ofChannelOnlyRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeTagGuestPolicy =
            BetaClaudeTagGuestPolicy.ofChannelOnly(
                BetaClaudeTagGuestPolicyChannelOnly.builder().build()
            )

        val roundtrippedBetaClaudeTagGuestPolicy =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeTagGuestPolicy),
                jacksonTypeRef<BetaClaudeTagGuestPolicy>(),
            )

        assertThat(roundtrippedBetaClaudeTagGuestPolicy).isEqualTo(betaClaudeTagGuestPolicy)
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
        val betaClaudeTagGuestPolicy =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BetaClaudeTagGuestPolicy>())

        val e = assertThrows<AnthropicInvalidDataException> { betaClaudeTagGuestPolicy.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
