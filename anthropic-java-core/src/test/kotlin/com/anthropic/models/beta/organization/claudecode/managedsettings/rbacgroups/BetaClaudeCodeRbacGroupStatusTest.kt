package com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroups

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaClaudeCodeRbacGroupStatusTest {

    @Test
    fun ofActive() {
        val active = BetaClaudeCodeRbacGroupStatusActive.builder().build()

        val betaClaudeCodeRbacGroupStatus = BetaClaudeCodeRbacGroupStatus.ofActive(active)

        assertThat(betaClaudeCodeRbacGroupStatus.active()).contains(active)
        assertThat(betaClaudeCodeRbacGroupStatus.deleted()).isEmpty
        assertThat(betaClaudeCodeRbacGroupStatus.unknown()).isEmpty
    }

    @Test
    fun ofActiveRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeCodeRbacGroupStatus =
            BetaClaudeCodeRbacGroupStatus.ofActive(
                BetaClaudeCodeRbacGroupStatusActive.builder().build()
            )

        val roundtrippedBetaClaudeCodeRbacGroupStatus =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeCodeRbacGroupStatus),
                jacksonTypeRef<BetaClaudeCodeRbacGroupStatus>(),
            )

        assertThat(roundtrippedBetaClaudeCodeRbacGroupStatus)
            .isEqualTo(betaClaudeCodeRbacGroupStatus)
    }

    @Test
    fun ofDeleted() {
        val deleted = BetaClaudeCodeRbacGroupStatusDeleted.builder().build()

        val betaClaudeCodeRbacGroupStatus = BetaClaudeCodeRbacGroupStatus.ofDeleted(deleted)

        assertThat(betaClaudeCodeRbacGroupStatus.active()).isEmpty
        assertThat(betaClaudeCodeRbacGroupStatus.deleted()).contains(deleted)
        assertThat(betaClaudeCodeRbacGroupStatus.unknown()).isEmpty
    }

    @Test
    fun ofDeletedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeCodeRbacGroupStatus =
            BetaClaudeCodeRbacGroupStatus.ofDeleted(
                BetaClaudeCodeRbacGroupStatusDeleted.builder().build()
            )

        val roundtrippedBetaClaudeCodeRbacGroupStatus =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeCodeRbacGroupStatus),
                jacksonTypeRef<BetaClaudeCodeRbacGroupStatus>(),
            )

        assertThat(roundtrippedBetaClaudeCodeRbacGroupStatus)
            .isEqualTo(betaClaudeCodeRbacGroupStatus)
    }

    @Test
    fun ofUnknown() {
        val unknown = BetaClaudeCodeRbacGroupStatusUnknown.builder().build()

        val betaClaudeCodeRbacGroupStatus = BetaClaudeCodeRbacGroupStatus.ofUnknown(unknown)

        assertThat(betaClaudeCodeRbacGroupStatus.active()).isEmpty
        assertThat(betaClaudeCodeRbacGroupStatus.deleted()).isEmpty
        assertThat(betaClaudeCodeRbacGroupStatus.unknown()).contains(unknown)
    }

    @Test
    fun ofUnknownRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeCodeRbacGroupStatus =
            BetaClaudeCodeRbacGroupStatus.ofUnknown(
                BetaClaudeCodeRbacGroupStatusUnknown.builder().build()
            )

        val roundtrippedBetaClaudeCodeRbacGroupStatus =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeCodeRbacGroupStatus),
                jacksonTypeRef<BetaClaudeCodeRbacGroupStatus>(),
            )

        assertThat(roundtrippedBetaClaudeCodeRbacGroupStatus)
            .isEqualTo(betaClaudeCodeRbacGroupStatus)
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
        val betaClaudeCodeRbacGroupStatus =
            jsonMapper()
                .convertValue(testCase.value, jacksonTypeRef<BetaClaudeCodeRbacGroupStatus>())

        val e =
            assertThrows<AnthropicInvalidDataException> { betaClaudeCodeRbacGroupStatus.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
