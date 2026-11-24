package com.anthropic.models.beta.organization.claudetag.bundles.targets

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

internal class BetaClaudeTagBundleTargetCreateTest {

    @Test
    fun ofSlackChannel() {
        val slackChannel =
            BetaClaudeTagSlackChannelBundleTargetCreate.of("slch_01SvpyRQK3nN6eH9uhRA7n1q")

        val betaClaudeTagBundleTargetCreate =
            BetaClaudeTagBundleTargetCreate.ofSlackChannel(slackChannel)

        assertThat(presentVariants(betaClaudeTagBundleTargetCreate))
            .isEqualTo(mapOf("slackChannel" to Optional.of(slackChannel)))
    }

    @Test
    fun ofSlackChannelRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeTagBundleTargetCreate =
            BetaClaudeTagBundleTargetCreate.ofSlackChannel(
                BetaClaudeTagSlackChannelBundleTargetCreate.of("slch_01SvpyRQK3nN6eH9uhRA7n1q")
            )

        val roundtrippedBetaClaudeTagBundleTargetCreate =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeTagBundleTargetCreate),
                jacksonTypeRef<BetaClaudeTagBundleTargetCreate>(),
            )

        assertThat(roundtrippedBetaClaudeTagBundleTargetCreate)
            .isEqualTo(betaClaudeTagBundleTargetCreate)
    }

    @Test
    fun ofSlackWorkspace() {
        val slackWorkspace = BetaClaudeTagSlackWorkspaceBundleTargetCreate.of("xxxxxxxx")

        val betaClaudeTagBundleTargetCreate =
            BetaClaudeTagBundleTargetCreate.ofSlackWorkspace(slackWorkspace)

        assertThat(presentVariants(betaClaudeTagBundleTargetCreate))
            .isEqualTo(mapOf("slackWorkspace" to Optional.of(slackWorkspace)))
    }

    @Test
    fun ofSlackWorkspaceRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeTagBundleTargetCreate =
            BetaClaudeTagBundleTargetCreate.ofSlackWorkspace(
                BetaClaudeTagSlackWorkspaceBundleTargetCreate.of("xxxxxxxx")
            )

        val roundtrippedBetaClaudeTagBundleTargetCreate =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeTagBundleTargetCreate),
                jacksonTypeRef<BetaClaudeTagBundleTargetCreate>(),
            )

        assertThat(roundtrippedBetaClaudeTagBundleTargetCreate)
            .isEqualTo(betaClaudeTagBundleTargetCreate)
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
        val betaClaudeTagBundleTargetCreate =
            jsonMapper()
                .convertValue(testCase.value, jacksonTypeRef<BetaClaudeTagBundleTargetCreate>())

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaClaudeTagBundleTargetCreate.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }

    private fun presentVariants(
        betaClaudeTagBundleTargetCreate: BetaClaudeTagBundleTargetCreate
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "slackChannel" to betaClaudeTagBundleTargetCreate.slackChannel(),
                "slackWorkspace" to betaClaudeTagBundleTargetCreate.slackWorkspace(),
            )
            .filterValues { it.isPresent }
}
