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

internal class BetaClaudeTagReplyModeTest {

    @Test
    fun ofAutomatic() {
        val automatic = BetaClaudeTagReplyModeAutomatic.builder().build()

        val betaClaudeTagReplyMode = BetaClaudeTagReplyMode.ofAutomatic(automatic)

        assertThat(betaClaudeTagReplyMode.automatic()).contains(automatic)
        assertThat(betaClaudeTagReplyMode.mentionOnly()).isEmpty
        assertThat(betaClaudeTagReplyMode.alwaysAwake()).isEmpty
        assertThat(betaClaudeTagReplyMode.thread()).isEmpty
    }

    @Test
    fun ofAutomaticRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeTagReplyMode =
            BetaClaudeTagReplyMode.ofAutomatic(BetaClaudeTagReplyModeAutomatic.builder().build())

        val roundtrippedBetaClaudeTagReplyMode =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeTagReplyMode),
                jacksonTypeRef<BetaClaudeTagReplyMode>(),
            )

        assertThat(roundtrippedBetaClaudeTagReplyMode).isEqualTo(betaClaudeTagReplyMode)
    }

    @Test
    fun ofMentionOnly() {
        val mentionOnly = BetaClaudeTagReplyModeMentionOnly.builder().build()

        val betaClaudeTagReplyMode = BetaClaudeTagReplyMode.ofMentionOnly(mentionOnly)

        assertThat(betaClaudeTagReplyMode.automatic()).isEmpty
        assertThat(betaClaudeTagReplyMode.mentionOnly()).contains(mentionOnly)
        assertThat(betaClaudeTagReplyMode.alwaysAwake()).isEmpty
        assertThat(betaClaudeTagReplyMode.thread()).isEmpty
    }

    @Test
    fun ofMentionOnlyRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeTagReplyMode =
            BetaClaudeTagReplyMode.ofMentionOnly(
                BetaClaudeTagReplyModeMentionOnly.builder().build()
            )

        val roundtrippedBetaClaudeTagReplyMode =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeTagReplyMode),
                jacksonTypeRef<BetaClaudeTagReplyMode>(),
            )

        assertThat(roundtrippedBetaClaudeTagReplyMode).isEqualTo(betaClaudeTagReplyMode)
    }

    @Test
    fun ofAlwaysAwake() {
        val alwaysAwake = BetaClaudeTagReplyModeAlwaysAwake.builder().build()

        val betaClaudeTagReplyMode = BetaClaudeTagReplyMode.ofAlwaysAwake(alwaysAwake)

        assertThat(betaClaudeTagReplyMode.automatic()).isEmpty
        assertThat(betaClaudeTagReplyMode.mentionOnly()).isEmpty
        assertThat(betaClaudeTagReplyMode.alwaysAwake()).contains(alwaysAwake)
        assertThat(betaClaudeTagReplyMode.thread()).isEmpty
    }

    @Test
    fun ofAlwaysAwakeRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeTagReplyMode =
            BetaClaudeTagReplyMode.ofAlwaysAwake(
                BetaClaudeTagReplyModeAlwaysAwake.builder().build()
            )

        val roundtrippedBetaClaudeTagReplyMode =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeTagReplyMode),
                jacksonTypeRef<BetaClaudeTagReplyMode>(),
            )

        assertThat(roundtrippedBetaClaudeTagReplyMode).isEqualTo(betaClaudeTagReplyMode)
    }

    @Test
    fun ofThread() {
        val thread = BetaClaudeTagReplyModeThread.builder().build()

        val betaClaudeTagReplyMode = BetaClaudeTagReplyMode.ofThread(thread)

        assertThat(betaClaudeTagReplyMode.automatic()).isEmpty
        assertThat(betaClaudeTagReplyMode.mentionOnly()).isEmpty
        assertThat(betaClaudeTagReplyMode.alwaysAwake()).isEmpty
        assertThat(betaClaudeTagReplyMode.thread()).contains(thread)
    }

    @Test
    fun ofThreadRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeTagReplyMode =
            BetaClaudeTagReplyMode.ofThread(BetaClaudeTagReplyModeThread.builder().build())

        val roundtrippedBetaClaudeTagReplyMode =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeTagReplyMode),
                jacksonTypeRef<BetaClaudeTagReplyMode>(),
            )

        assertThat(roundtrippedBetaClaudeTagReplyMode).isEqualTo(betaClaudeTagReplyMode)
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
        val betaClaudeTagReplyMode =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BetaClaudeTagReplyMode>())

        val e = assertThrows<AnthropicInvalidDataException> { betaClaudeTagReplyMode.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
